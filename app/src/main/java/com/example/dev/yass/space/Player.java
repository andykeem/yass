package com.example.dev.yass.space;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dev.yass.R;
import com.example.dev.yass.engine.BaseInputController;
import com.example.dev.yass.engine.GameEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dev on 5/2/16.
 */
public class Player extends Sprite {

    private static final String TAG = "Player";
    protected static final double DEFAULT_HIGHT = 400d;
    protected static final double DEFAULT_MOVE_UNIT = 100d;
    protected static final int TIME_BETWEEN_BULLETS = 100;
    protected static final int NUM_BULLETS = 6;

    protected double mSpeedFactor;
    protected int mMaxX, mMaxY;
    protected BaseInputController mInputController;
    protected List<Bullet> mBullets = new ArrayList<>();
    protected double mTimeSinceLastFire;
    protected GameEngine mGameEngine;

    public Player(GameEngine engine) {
        super(engine, R.drawable.ship);
        mGameEngine = engine;

        mSpeedFactor = DEFAULT_MOVE_UNIT / 1000d;

        mMaxX = engine.mViewWidth - mImgWidth;
        mMaxY = engine.mViewHeight - mImgHeight;

        this.initBulletPool();
    }

    @Override
    public void startGame() {
        mPosX = (mMaxX / 2);
        mPosY = (mMaxY / 2);
    }

    @Override
    public void onUpdate(long elapsedMillis, GameEngine engine) {
        this.updatePlayer(elapsedMillis, engine);
        this.updateBullet(elapsedMillis, engine);
    }

    protected void updatePlayer(long elapsedMillis, GameEngine engine) {
        mInputController = (JoystickController) engine.mInputController;
        mPosX += (mInputController.mHorizontalFactor * mSpeedFactor * elapsedMillis);
        mPosY += (mInputController.mVerticalFactor * mSpeedFactor * elapsedMillis);
        if (mPosX > mMaxX) {
            mPosX = mMaxX;
        }
        if (mPosX < 0) {
            mPosX = 0;
        }
        if (mPosY > mMaxY) {
            mPosY = mMaxY;
        }
        if (mPosY < 0) {
            mPosY = 0;
        }
    }

    protected void updateBullet(long elapsedMillis, GameEngine engine) {
//        Log.d(TAG, "Time since last fire: " + mTimeSinceLastFire);
        if (mInputController.mFired && (mTimeSinceLastFire > TIME_BETWEEN_BULLETS)) {
            Bullet b = this.getBullet();
            if (b == null) {
                return;
            }
            double x = (mPosX + (mImgWidth / 2));
            b.init(this, x, mPosY);
            engine.addGameObject(b);
            mTimeSinceLastFire = 0;
        } else {
            mTimeSinceLastFire += elapsedMillis;
        }
    }

    protected void initBulletPool() {
        for (int i = 0; i < NUM_BULLETS; i++) {
            mBullets.add(new Bullet(mGameEngine, R.drawable.bullet));
        }
    }

    protected Bullet getBullet() {
        if (mBullets.isEmpty()) {
            return null;
        }
        return mBullets.remove(0);
    }

    protected void releaseBullet(Bullet b) {
        mBullets.add(b);
    }
}

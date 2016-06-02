package com.example.dev.yass.space;

import android.graphics.Canvas;

import com.example.dev.yass.engine.GameEngine;

/**
 * Created by dev on 5/22/16.
 */
public class Asteroid extends Sprite {

    protected static final double UNIT_TO_MOVE = 100d;
    protected static final double ROTATION_TIME_MILLIS = 250d;

    protected GameController mGameController;
    protected double mSpeed;
    protected double mSpeedX, mSpeedY;
    protected float mRotation;
    protected double mRotationSpeed;

    public Asteroid(GameEngine engine, GameController controller, int drawableResId) {
        super(engine, drawableResId);
        mGameController = controller;
        mSpeed = (UNIT_TO_MOVE * mPixelFactor) / 1000d;
        mRotation = engine.mRandom.nextInt(360);
    }

    public void init(GameEngine engine) {
        // get speeds for x and y axis
        double angle = ((engine.mRandom.nextDouble() * (Math.PI / 3d)) - (Math.PI / 6d));
        // get x, y positions
        mSpeedX = Math.sin(angle) * mSpeed;
        mSpeedY = Math.cos(angle) * mSpeed;

        mPosX = engine.mRandom.nextInt(engine.mViewWidth / 2) + (engine.mViewWidth / 4);
        mPosY = -mImgHeight;

        mRotationSpeed = angle * (180 / Math.PI) / ROTATION_TIME_MILLIS;
    }

    public void onUpdate(long elapsedMillis, GameEngine engine) {
        mPosX += mSpeedX * elapsedMillis;
        mPosY += mSpeedY * elapsedMillis;
        if (mPosY > engine.mViewHeight) {
            engine.removeGameObject(this);
            mGameController.returnToPool(this);
        }
        // update rotation..
        mRotation += mRotationSpeed * elapsedMillis;
        if (mRotation > 360) {
            mRotation = 0;
        } else if (mRotation < 0) {
            mRotation = 360;
        }
    }

    public void onDraw(Canvas canvas) {
        mMatrix.reset();
        mMatrix.postScale((float) mPixelFactor, (float) mPixelFactor);
        mMatrix.postTranslate((float) mPosX, (float) mPosY);
        mMatrix.postRotate(mRotation, (float) (mPosX + mImgWidth / 2), (float) (mPosY + mImgHeight / 2));
        canvas.drawBitmap(mImgBitmap, mMatrix, null);
    }
}

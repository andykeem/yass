package com.example.dev.yass.space;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.dev.yass.R;
import com.example.dev.yass.engine.GameEngine;
import com.example.dev.yass.engine.GameObject;

/**
 * Created by dev on 5/3/16.
 */
public class Bullet extends Sprite {

    private static final String TAG = "Bullet";
    protected static final double UNIT_TO_MOVE = -300d;

    protected double mSpeedFactor;
    protected Player mPlayer;

    public Bullet(GameEngine engine, int drawableResId) {
        super(engine, drawableResId);

        mSpeedFactor = engine.mPixelFactor * UNIT_TO_MOVE / 1000d;
    }

    @Override
    public void onUpdate(long elapsedMillis, GameEngine engine) {
        mPosY += mSpeedFactor * elapsedMillis;
        if (mPosY < -mImgHeight) {
            engine.removeGameObject(this);
            mPlayer.releaseBullet(this);
        }
    }

    public void init(Player player, double x, double y) {
        mPosX = x - (mImgWidth / 2);
        mPosY = y - (mImgHeight / 2);
        mPlayer = player;
    }
}

package com.example.dev.yass.space;

import com.example.dev.yass.R;
import com.example.dev.yass.engine.GameEngine;

import java.util.Random;

/**
 * Created by dev on 5/22/16.
 */
public class Asteroid extends Sprite {

    protected static final double UNIT_TO_MOVE = 100d;
    protected GameController mGameController;
    protected double mSpeed;
    protected double mSpeedX, mSpeedY;
    protected int mViewH, mViewW;

    public Asteroid(GameEngine engine, GameController controller, int drawableResId) {
        super(engine, drawableResId);
        mGameController = controller;
        mSpeed = (UNIT_TO_MOVE * mPixelFactor) / 1000d;
    }

    public void init(GameEngine engine) {
        // get speeds for x and y axis
        double angle = ((engine.mRandom.nextDouble() * (Math.PI / 3d)) - (Math.PI / 6d));
        // get x, y positions
        mSpeedX = Math.sin(angle) * mSpeed;
        mSpeedY = Math.cos(angle) * mSpeed;

        mPosX = engine.mRandom.nextInt(engine.mViewWidth / 2) + (engine.mViewWidth / 4);
        mPosY = -mImgHeight;
    }

    public void onUpdate(long elapsedMillis, GameEngine engine) {
        mPosX += mSpeedX * elapsedMillis;
        mPosY += mSpeedY * elapsedMillis;
        if (mPosY > engine.mViewHeight) {
            engine.removeGameObject(this);
            mGameController.returnToPool(this);
        }
    }
}

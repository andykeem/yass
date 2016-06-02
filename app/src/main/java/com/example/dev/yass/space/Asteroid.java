package com.example.dev.yass.space;

import com.example.dev.yass.engine.GameEngine;

/**
 * Created by dev on 6/1/16.
 */
public class Asteroid extends Sprite {

    protected static final String TAG = "Asteroid";
    protected static final double UNIT_TO_MOVE = 200d;
    protected double mSpeed;
    protected double mSpeedX, mSpeedY;

    public Asteroid(GameEngine engine, int drawableResId) {
        super(engine, drawableResId);

        mSpeed = (UNIT_TO_MOVE / 1000d) * engine.mPixelFactor;
    }

    public void init(GameEngine engine, long elapsedMillis) {
        // initialize position of asteroids..
        // drop it 30 degree angle
        double radian = ((mGameEngine.mRandom.nextDouble() * (Math.PI / 3)) - Math.PI / 6);
        mSpeedX = (Math.cos(radian) * mGameEngine.mPixelFactor) / elapsedMillis;
        mSpeedY = (Math.sin(radian) * mGameEngine.mPixelFactor) / elapsedMillis;

        // drop it from the center of the screen
        int halfScreen = mGameEngine.mViewWidth / 2;
        mPosX = (mGameEngine.mRandom.nextInt(halfScreen) + (mGameEngine.mViewWidth / 4));
        mPosY -= mImgHeight;
    }

    public void onUpdate(long elapsedMillis, GameEngine engine) {
        mPosY += 100d;
    }
}
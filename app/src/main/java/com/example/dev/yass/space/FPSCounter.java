package com.example.dev.yass.space;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.example.dev.yass.engine.GameEngine;
import com.example.dev.yass.engine.GameObject;
import com.example.dev.yass.view.GameView;

/**
 * Created by dev on 5/17/16.
 */
public class FPSCounter extends GameObject {

    protected static final String TAG = "FPSCounter";
    protected static final int TEXT_HEIGHT = 25;
    protected static final int TEXT_WIDTH = 50;

    protected double mPixelFactor;
    protected Paint mPaint;
    protected long mTotalMillis;
    protected int mNumDraws;
    protected float mTextHeight, mTextWidth;
    protected float mFps;
    protected String mFpsText = "";

    public FPSCounter(GameEngine engine) {
        mPixelFactor = engine.mPixelFactor;

        mTextHeight = (float) (TEXT_HEIGHT * mPixelFactor);
        mTextWidth = (float) (TEXT_WIDTH * mPixelFactor);

        mPaint = new Paint();
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(mTextHeight / 2);
    }

    public void startGame() {
        mTotalMillis = 0;
    }

    public void onUpdate(long elapsedMillis, GameEngine engine) {
        mTotalMillis += elapsedMillis;
        if (mTotalMillis > 1000) {
            mFps = 1000 * mNumDraws / mTotalMillis;
            mFpsText = mFps + " fps";
            mTotalMillis = 0;
            mNumDraws = 0;
        }
    }

    public void onDraw(Canvas canvas) {
        mPaint.setColor(Color.BLACK);
        float left = 0,
                top = canvas.getHeight() - mTextHeight,
                right = mTextWidth,
                bottom = canvas.getHeight();
        canvas.drawRect(left, top, right, bottom, mPaint);

        mPaint.setColor(Color.WHITE);
        float x = mTextWidth / 2,
                y = canvas.getHeight() - (mTextHeight / 2);
        canvas.drawText(mFpsText, x, y, mPaint);
        mNumDraws++;
    }
}
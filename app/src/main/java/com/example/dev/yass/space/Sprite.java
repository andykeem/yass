package com.example.dev.yass.space;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.example.dev.yass.engine.GameEngine;
import com.example.dev.yass.engine.GameObject;

/**
 * Created by dev on 5/13/16.
 */
public abstract class Sprite extends GameObject {

    protected double mPosX;
    protected double mPosY;

    protected Context mContext;
    protected double mPixelFactor;
    protected int mImgHeight;
    protected int mImgWidth;
    protected Bitmap mImgBitmap;

    protected Matrix mMatrix = new Matrix();

    protected Sprite(GameEngine engine, int drawableResId) {
        mContext = engine.mContext;
        mPixelFactor = engine.mPixelFactor;
        Drawable drawable = ContextCompat.getDrawable(engine.mContext, drawableResId);

        mImgHeight = (int) (drawable.getIntrinsicHeight() * mPixelFactor);
        mImgWidth = (int) (drawable.getIntrinsicWidth() * mPixelFactor);

        mImgBitmap = ((BitmapDrawable) drawable).getBitmap();
    }

    @Override
    public void onDraw(Canvas canvas) {
        mMatrix.reset();
        mMatrix.postScale((float) mPixelFactor, (float) mPixelFactor);
        mMatrix.postTranslate((float) mPosX, (float) mPosY);
        canvas.drawBitmap(mImgBitmap, mMatrix, null);
    }
}
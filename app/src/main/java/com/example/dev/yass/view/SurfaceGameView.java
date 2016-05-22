package com.example.dev.yass.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.dev.yass.engine.GameObject;

import java.util.List;

/**
 * Created by dev on 5/12/16.
 */
public class SurfaceGameView extends SurfaceView implements SurfaceHolder.Callback, GameView {

    protected boolean mReady;
    protected List<GameObject> mGameObjects;

    public SurfaceGameView(Context context) {
        super(context);
        this.getHolder().addCallback(this);
    }

    public SurfaceGameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.getHolder().addCallback(this);
    }

    public SurfaceGameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mReady = true;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mReady = false;
    }

    @Override
    public void draw() {
        if (!mReady) {
            return;
        }
        Canvas canvas = this.getHolder().lockCanvas();
        if (canvas == null) {
            return;
        }
        canvas.drawRGB(0, 0, 0);
        synchronized (mGameObjects) {
            int numObjs = mGameObjects.size();
            for (int i = 0; i < numObjs; i++) {
                mGameObjects.get(i).onDraw(canvas);
            }
        }
        this.getHolder().unlockCanvasAndPost(canvas);
    }

    @Override
    public void setGameObjects(List<GameObject> objs) {
        mGameObjects = objs;
    }
}

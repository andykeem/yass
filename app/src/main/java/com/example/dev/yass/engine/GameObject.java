package com.example.dev.yass.engine;

import android.graphics.Canvas;

/**
 * Created by dev on 4/29/16.
 */
public abstract class GameObject {

    public Runnable mOnAddedRunnable = new Runnable() {
        @Override
        public void run() {
            onAddedToUiThread();
        }
    };
    public Runnable mOnRemovedFromRunnable = new Runnable() {
        @Override
        public void run() {
            onRemovedFromUiThread();
        }
    };

    public void onAddedToUiThread() {

    }

    public void onRemovedFromUiThread() {

    }

    public void startGame() {

    }

    public void onUpdate(long elapsedMillis, GameEngine engine) {

    }

    public void onDraw(Canvas c) {

    }
}

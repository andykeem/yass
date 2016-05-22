package com.example.dev.yass.engine;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dev on 4/29/16.
 */
public class DrawThread extends Thread {

    protected static final int MIN_TIME_TO_DRAW = 20;
    protected GameEngine mGameEngine;
    protected boolean mRunning, mPaused;
    protected Lock mLock;

    public DrawThread(GameEngine engine) {
        mGameEngine = engine;
        mLock = new ReentrantLock();
    }

    @Override
    public void start() {
        mRunning = true;
        super.start();
    }

    @Override
    public void run() {
        long prevMillis, currentMillis, elapsedMillis;
        prevMillis = System.currentTimeMillis();
        while (mRunning) {
            currentMillis = System.currentTimeMillis();
            elapsedMillis = (currentMillis - prevMillis);
            if (mPaused) {
                while (mPaused) {
                    try {
                        synchronized (mLock) {
                            mLock.wait();
                        }
                    } catch (InterruptedException ie) {
                        // skip the exception..
                    }
                }
                currentMillis = System.currentTimeMillis();
            }
            if (elapsedMillis < MIN_TIME_TO_DRAW) {
                try {
                    Thread.sleep(MIN_TIME_TO_DRAW - elapsedMillis);
                } catch (InterruptedException ie) {
                    // skip the exception..
                }
            }
            mGameEngine.onDraw();
            prevMillis = currentMillis;
        }
    }

    public void stopGame() {
        if (mRunning) {
            mRunning = false;
        }
    }

    public void pauseGame() {
        mPaused = true;
    }

    public void resumeGame() {
        if (mPaused) {
            mPaused = false;
            mLock.notify();
        }
    }
}

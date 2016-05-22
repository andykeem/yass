package com.example.dev.yass.engine;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dev on 4/29/16.
 */
public class UpdateThread extends Thread {

    protected GameEngine mGameEngine;
    protected volatile boolean mRunning;
    protected volatile boolean mPaused;
    protected Lock mLock;

    public UpdateThread(GameEngine engine) {
        mGameEngine = engine;
        mLock = new ReentrantLock();
    }

    @Override
    public void run() {
        long prevMillis, currMillis, elapsedMillis;
        prevMillis = System.currentTimeMillis();
        while (mRunning) {
            currMillis = System.currentTimeMillis();
            elapsedMillis = (currMillis - prevMillis);
            if (mPaused) {
                while (mPaused) {
                    try {
                        synchronized (mLock) {
                            mLock.wait();
                        }
                    } catch (InterruptedException ie) {
                        // continue..
                    }
                }
                currMillis = System.currentTimeMillis();
            }
            mGameEngine.onUpdate(elapsedMillis);
            prevMillis = currMillis;
        }
    }

    public void start() {
        mRunning = true;
        mPaused = false;
        super.start();
    }

    public boolean isRunning() {
        return mRunning;
    }

    public void stopGame() {
        if (mRunning) {
            mRunning = false;
            this.resumeGame();
        }
    }

    public void pauseGame() {
        mPaused = true;
    }

    public void resumeGame() {
        if (mPaused) {
            mPaused = false;
            synchronized (mLock) {
                mLock.notify();
            }
        }
    }

    public boolean isPaused() {
        return mPaused;
    }
}

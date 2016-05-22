package com.example.dev.yass.engine;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.example.dev.yass.view.GameView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by dev on 4/29/16.
 */
public class GameEngine {

    protected static final double DEFAULT_HIGHT = 400d;

    public Context mContext;
    protected List<GameObject> mGameObjects = new ArrayList<>();
    protected List<GameObject> mObjetsToAdd = new ArrayList<>();
    protected List<GameObject> mObjectsToRemove = new ArrayList<>();
    protected UpdateThread mUpdateThread;
    protected DrawThread mDrawThread;
    /*public Runnable mOnDrawRunnable = new Runnable() {
        @Override
        public void run() {
            int numGameObjects = mGameObjects.size();
            for (int i = 0; i < numGameObjects; i++) {
                mGameObjects.get(i).onDraw();
            }
        }
    };*/
    public BaseInputController mInputController;
    public GameView mGameView;
    public double mPixelFactor;
    public int mViewWidth, mViewHeight;
    public Random mRandom;

    public GameEngine(Context context, GameView view) {
        mContext = context;
        mGameView = view;
        mGameView.setGameObjects(mGameObjects);
        mPixelFactor = ((View) mGameView).getHeight() / DEFAULT_HIGHT;

        mViewWidth = view.getWidth() - view.getPaddingLeft() - view.getPaddingRight();
        mViewHeight = view.getHeight() - view.getPaddingTop() - view.getPaddingBottom();
        mRandom = new Random();
    }

    public void startGame() {
        this.stopGame();
        int numGameObjects = mGameObjects.size();
        for (int i = 0; i < numGameObjects; i++) {
            mGameObjects.get(i).startGame();
        }

        mUpdateThread = new UpdateThread(this);
        mUpdateThread.start();

        mDrawThread = new DrawThread(this);
        mDrawThread.start();
    }

    public void stopGame() {
        if (mUpdateThread != null) {
            mUpdateThread.stopGame();
        }
        if (mDrawThread != null) {
            mDrawThread.stopGame();
        }
    }

    public void onUpdate(long elapsedMillis) {
        int numGameObjects = mGameObjects.size();
        for (int i = 0; i < numGameObjects; i++) {
            mGameObjects.get(i).onUpdate(elapsedMillis, this);
        }
        if (this.isRunning()) {
            synchronized (mGameObjects) {
                while (!mObjectsToRemove.isEmpty()) {
                    mGameObjects.remove(mObjectsToRemove.remove(0));
                }
                while (!mObjetsToAdd.isEmpty()) {
                    mGameObjects.add(mObjetsToAdd.remove(0));
                }
            }
        }
    }

    public void onDraw() {
//        ((Activity) mContext).runOnUiThread(mOnDrawRunnable);
        mGameView.draw();
    }

    public boolean isRunning() {
        return ((mUpdateThread != null) && mUpdateThread.isRunning());
    }

    public void addGameObject(GameObject obj) {
        if (this.isRunning()) {
            mObjetsToAdd.add(obj);
        } else {
            mGameObjects.add(obj);
        }
        ((Activity) mContext).runOnUiThread(obj.mOnAddedRunnable);
    }

    public void removeGameObject(GameObject obj) {
        if (this.isRunning()) {
            mObjectsToRemove.add(obj);
        } else {
            mGameObjects.remove(obj);
        }
        ((Activity) mContext).runOnUiThread(obj.mOnRemovedFromRunnable);
    }

    public void pauseGame() {
        if (this.isRunning()) {
            mUpdateThread.pauseGame();
        }
    }

    public boolean isPaused() {
        return ((mUpdateThread != null) && mUpdateThread.isPaused());
    }

    public void resumeGame() {
        if (this.isPaused()) {
            mUpdateThread.resumeGame();
        }
    }

    public void setInputController(BaseInputController inputController) {
        mInputController = inputController;
    }
}

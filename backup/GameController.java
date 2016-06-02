package com.example.dev.yass.space;

import com.example.dev.yass.R;
import com.example.dev.yass.engine.GameEngine;
import com.example.dev.yass.engine.GameObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dev on 5/20/16.
 */
public class GameController extends GameObject {

    protected static final String TAG = "GameController";
    protected static final int TIME_BETWEEN_ENEMIES = 500;
    protected int NUM_ASTEROIDS = 10;
    protected GameEngine mGameEngine;
    protected List<GameObject> mAsteroids = new ArrayList<>();
    protected long mCurrMillis;
    protected int mNumSpawned;

    public GameController(GameEngine engine) {
        mGameEngine = engine;
        this.setAsteroids();
    }

    protected void setAsteroids() {
        for (int i = 0; i < NUM_ASTEROIDS; i++) {
            int drawableResId = R.drawable.a10000;
            switch (i) {
                case 1:
                    drawableResId = R.drawable.a10001;
                    break;
                case 2:
                    drawableResId = R.drawable.a10002;
                    break;
                case 3:
                    drawableResId = R.drawable.a10003;
                    break;
                case 4:
                    drawableResId = R.drawable.a10004;
                    break;
                case 5:
                    drawableResId = R.drawable.a10005;
                    break;
            }
            Asteroid a = new Asteroid(mGameEngine, this, drawableResId);
            mAsteroids.add(a);
        }
    }

    public void returnToPool(Asteroid a) {
        mAsteroids.add(a);
    }

    public void onUpdate(long elapsedMillis, GameEngine engine) {
        mCurrMillis += elapsedMillis;
        long waveTime = (TIME_BETWEEN_ENEMIES * mNumSpawned);
        if (mCurrMillis > waveTime) {
            if (!mAsteroids.isEmpty()) {
                Asteroid a = (Asteroid) mAsteroids.remove(0);
                a.init(engine);
                engine.addGameObject(a);
            }
            mNumSpawned++;
        }
    }
}
package com.example.dev.yass.engine;

import com.example.dev.yass.R;
import com.example.dev.yass.space.Asteroid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dev on 6/1/16.
 */
public class GameController extends GameObject {

    protected static final int NUM_ASTEROIDS = 6;
    public GameEngine mGameEngine;
    protected List<Asteroid> mAsteroidPool = new ArrayList<>();

    public GameController(GameEngine engine) {
        mGameEngine = engine;
        this.generateAsteroids();

    }

    @Override
    public void onUpdate(long elapsedMillis, GameEngine engine) {
        while (!mAsteroidPool.isEmpty()) {
            Asteroid a = mAsteroidPool.remove(0);
            a.init(engine, elapsedMillis);
            mGameEngine.addGameObject(a);
        }
    }

    protected void generateAsteroids() {
        for (int i = 0; i < NUM_ASTEROIDS; i++) {
            int resId = R.drawable.a10000;
            switch (i) {
                case 1:
                    resId = R.drawable.a10001;
                    break;
                case 2:
                    resId = R.drawable.a10002;
                    break;
                case 3:
                    resId = R.drawable.a10003;
                    break;
                case 4:
                    resId = R.drawable.a10004;
                    break;
                case 5:
                    resId = R.drawable.a10005;
                    break;
            }
            Asteroid a = new Asteroid(mGameEngine, resId);
            mAsteroidPool.add(a);
        }
    }
}
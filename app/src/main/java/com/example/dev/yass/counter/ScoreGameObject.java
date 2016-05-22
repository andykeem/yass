package com.example.dev.yass.counter;

import android.graphics.Canvas;
import android.view.View;
import android.widget.TextView;

import com.example.dev.yass.engine.GameEngine;
import com.example.dev.yass.engine.GameObject;

/**
 * Created by dev on 4/29/16.
 */
public class ScoreGameObject extends GameObject {

    protected TextView mTxtScore;
    protected long mCounter;

    public ScoreGameObject(View view, int layoutResId) {
        mTxtScore = (TextView) view.findViewById(layoutResId);
    }

    @Override
    public void startGame() {
        mCounter = 0;
    }

    @Override
    public void onUpdate(long elapsedMillis, GameEngine engine) {
        mCounter += elapsedMillis;
    }

    @Override
    public void onDraw(Canvas c) {
        mTxtScore.setText(String.valueOf(mCounter));
    }
}

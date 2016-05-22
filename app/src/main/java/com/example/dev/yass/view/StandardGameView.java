package com.example.dev.yass.view;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import com.example.dev.yass.engine.GameObject;

import java.util.List;

/**
 * Created by dev on 5/12/16.
 */
public class StandardGameView extends View implements GameView {

    protected Context mContext;
    protected List<GameObject> mGameObjects;

    public StandardGameView(Context c) {
        super(c);
        mContext = c;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        synchronized (mGameObjects) {
            int numObjs = mGameObjects.size();
            for (int i = 0; i < numObjs; i++) {
                mGameObjects.get(i).onDraw(canvas);
            }
        }
    }

    @Override
    public void draw() {
        this.postInvalidate();
    }

    @Override
    public void setGameObjects(List<GameObject> objs) {
        mGameObjects = objs;
    }
}

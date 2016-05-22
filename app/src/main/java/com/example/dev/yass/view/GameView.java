package com.example.dev.yass.view;

import android.content.Context;
import android.graphics.Canvas;

import com.example.dev.yass.engine.GameObject;

import java.util.List;

/**
 * Created by dev on 5/12/16.
 */
public interface GameView {

    void draw();
    void setGameObjects(List<GameObject> objs);
    // generic methods from View class
    int getWidth();
    int getHeight();
    int getPaddingLeft();
    int getPaddingRight();
    int getPaddingTop();
    int getPaddingBottom();
    Context getContext();
}

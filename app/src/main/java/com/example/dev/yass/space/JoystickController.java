package com.example.dev.yass.space;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.dev.yass.R;
import com.example.dev.yass.engine.BaseInputController;

/**
 * Created by dev on 5/5/16.
 */
public class JoystickController extends BaseInputController {

    protected static final String TAG = "JoystickController";
    protected static final int MAX_DISTANCE_UNIT = 20;
    protected static double mMaxDistance;
    protected float mInitX;
    protected float mInitY;

    public JoystickController(View view) {
        view.findViewById(R.id.joystick_control).setOnTouchListener(new OnControlStickTouchListener());
        view.findViewById(R.id.joystick_fire).setOnTouchListener(new OnFireButtonTouchListener());

        double pixelFactor = view.getHeight() / Player.DEFAULT_HIGHT;
        mMaxDistance = pixelFactor * MAX_DISTANCE_UNIT;
    }

    private class OnControlStickTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int action = event.getActionMasked();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    mInitX = event.getX(0);
                    mInitY = event.getY(0);
                    break;
                case MotionEvent.ACTION_MOVE:
                    mHorizontalFactor = (int) ((event.getX(0) - mInitX) / mMaxDistance);
                    if (mHorizontalFactor < -1) {
                        mHorizontalFactor = -1;
                    } else if (mHorizontalFactor > 1) {
                        mHorizontalFactor = 1;
                    }
                    mVerticalFactor = (int) ((event.getY(0) - mInitY) / mMaxDistance);
                    if (mVerticalFactor < -1) {
                        mVerticalFactor = -1;
                    } else if (mVerticalFactor > 1) {
                        mVerticalFactor = 1;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    mInitX = 0;
                    mInitY = 0;
                    break;
            }
            return true;
        }
    }

    private class OnFireButtonTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int action = event.getActionMasked();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    mFired = true;
                    break;
                case MotionEvent.ACTION_UP:
                    mFired = false;
                    break;
            }
            return true;
        }
    }
}

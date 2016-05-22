package com.example.dev.yass.space;

import android.view.MotionEvent;
import android.view.View;

import com.example.dev.yass.R;
import com.example.dev.yass.engine.BaseInputController;

/**
 * Created by dev on 5/4/16.
 */
public class InputController extends BaseInputController implements View.OnTouchListener {

    public InputController(View view) {
        view.findViewById(R.id.btn_keypad_down).setOnTouchListener(this);
        view.findViewById(R.id.btn_keypad_left).setOnTouchListener(this);
        view.findViewById(R.id.btn_keypad_right).setOnTouchListener(this);
        view.findViewById(R.id.btn_keypad_up).setOnTouchListener(this);
        view.findViewById(R.id.btn_keypad_fire).setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getActionMasked();
        int id = v.getId();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                switch (id) {
                    case R.id.btn_keypad_down:
                        mVerticalFactor += 1;
                        break;
                    case R.id.btn_keypad_left:
                        mHorizontalFactor -= 1;
                        break;
                    case R.id.btn_keypad_right:
                        mHorizontalFactor += 1;
                        break;
                    case R.id.btn_keypad_up:
                        mVerticalFactor -= 1;
                        break;
                    case R.id.btn_keypad_fire:
                        mFired = true;
                        break;
                }
                break;
            case MotionEvent.ACTION_UP:
                switch (id) {
                    case R.id.btn_keypad_down:
                        mVerticalFactor -= 1;
                        break;
                    case R.id.btn_keypad_left:
                        mHorizontalFactor += 1;
                        break;
                    case R.id.btn_keypad_right:
                        mHorizontalFactor -= 1;
                        break;
                    case R.id.btn_keypad_up:
                        mVerticalFactor += 1;
                        break;
                    case R.id.btn_keypad_fire:
                        mFired = false;
                        break;
                }
                break;
        }
        return false;
    }
}

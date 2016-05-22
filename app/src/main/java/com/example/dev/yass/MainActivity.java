package com.example.dev.yass;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.dev.yass.counter.YassBaseFragment;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    protected static final String TAG = "MainActivity";
    protected static final String TAG_FRAGMENT = "TAG_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = this.getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.fragment_container);
        if (f == null) {
            f = new MainFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, f, TAG_FRAGMENT)
                    .commit();
        }

        /*Log.d(TAG, "PI: " + Math.PI);
        Random rand = new Random();
        String s = "";
        for (int i = 0; i < 100; i++) {
            int n = (int) (180 / Math.PI);
            double angle = rand.nextInt(n) - 30;
            s += angle + " | ";
        }
        Log.d(TAG, "angle: " + s);*/

        Random rand = new Random();
        double angle = (rand.nextDouble() * Math.PI / 3d) - Math.PI / 6d;
        Log.d(TAG, "angle: " + angle);
        Log.d(TAG, "cos(" + angle + "): " + Math.cos(angle));
        Log.d(TAG, "sin(" + angle + "): " + Math.sin(angle));
    }

    @Override
    public void onBackPressed() {
        YassBaseFragment f = (YassBaseFragment) this.getSupportFragmentManager()
                .findFragmentByTag(TAG_FRAGMENT);
        if (!f.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            View decorView = this.getWindow().getDecorView();
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LOW_PROFILE);
            } else {
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                );
            }
        }
    }
}

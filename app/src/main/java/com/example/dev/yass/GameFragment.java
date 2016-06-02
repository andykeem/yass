package com.example.dev.yass;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

import com.example.dev.yass.counter.YassBaseFragment;
import com.example.dev.yass.engine.GameController;
import com.example.dev.yass.engine.GameEngine;
import com.example.dev.yass.engine.GameObject;
import com.example.dev.yass.space.FPSCounter;
//import com.example.dev.yass.space.GameController;
import com.example.dev.yass.space.JoystickController;
import com.example.dev.yass.space.Player;
import com.example.dev.yass.view.GameView;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends YassBaseFragment {

    protected TextView mTxtScore;
    protected Button mBtnPause;
    protected GameEngine mGameEngine;
    protected GameView mGameView;

    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        mGameView = (GameView) view.findViewById(R.id.game_view);
        final ViewTreeObserver vto = view.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            @SuppressWarnings("deprecation")
            public void onGlobalLayout() {
                ViewTreeObserver obs = getView().getViewTreeObserver();
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    obs.removeGlobalOnLayoutListener(this);
                } else {
                    obs.removeOnGlobalLayoutListener(this);
                }
                mGameEngine = new GameEngine(getActivity(), mGameView);
                mGameEngine.setInputController(new JoystickController(getView()));
                mGameEngine.addGameObject(new Player(mGameEngine));
                mGameEngine.addGameObject(new FPSCounter(mGameEngine));
                mGameEngine.addGameObject(new GameController(mGameEngine));
                mGameEngine.startGame();
            }
        });

        mBtnPause = (Button) view.findViewById(R.id.btn_pause);
        mBtnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPauseAlert();
            }
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        mGameEngine.pauseGame();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mGameEngine.stopGame();
    }

    protected void showPauseAlert() {
        mGameEngine.pauseGame();
        new AlertDialog.Builder(this.getActivity())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.pause_alert_title)
                .setMessage(R.string.pause_alert_message)
                .setNegativeButton(R.string.btn_stop, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        navigateBack();
                    }
                })
                .setPositiveButton(R.string.btn_resume, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        mGameEngine.resumeGame();
                    }
                })
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        dialog.dismiss();
                        mGameEngine.resumeGame();
                    }
                })
                .show();
    }

    protected void navigateBack() {
        this.getActivity().onBackPressed();
    }

    @Override
    public boolean onBackPressed() {
        if (!mGameEngine.isPaused()) {
            showPauseAlert();
            return true;
        }
        return false;
    }
}

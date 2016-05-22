package com.example.dev.yass;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dev.yass.counter.YassBaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends YassBaseFragment {

    protected Button mBtnStart;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mBtnStart = (Button) view.findViewById(R.id.btn_start_game);
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new GameFragment(), MainActivity.TAG_FRAGMENT)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }
}

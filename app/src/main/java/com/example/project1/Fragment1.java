package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment1 extends Fragment {
    private static final String TAG = "Fragment 1";

    //creating variables
    private Button btStart, btRules, btHistory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //When inflating and returning a Fragment’s View in onCreateView(), be sure to pass in false for attachToRoot.
        //If you pass in true, you will get an IllegalStateException because the specified child already has a parent.
        View view = inflater.inflate(R.layout.list_fragment1_layout, container, false);

        //initializing variables
        btStart = (Button) view.findViewById(R.id.btnNavStart); //begin quiz
        btHistory= (Button) view.findViewById(R.id.btnNavHistory); //show user table
        btRules = (Button) view.findViewById(R.id.btnNavRules); //show rules activity
        Log.d(TAG, "onCreateView: Started");

        //button onClicks Below
        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CardquizActivity)getActivity()).setViewPager(1); //Launch Frag
            }
        });

        btHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                //Launch History Activity
                Intent intent = new Intent(getActivity(), HistoryActivity.class);
                startActivity(intent);
            }
        });

        btRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v3) {
                //Launch Rules Activity
                Intent intent = new Intent(getActivity(), RulesActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}

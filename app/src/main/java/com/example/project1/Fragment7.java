package com.example.project1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Fragment7 extends Fragment {
    private static final String TAG = "Results Fragment 7";

    //creating variables
    private Button btEnd, btScore;
    private TextView textView;
    int result;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //When inflating and returning a Fragment’s View in onCreateView(), be sure to pass in false for attachToRoot.
        //If you pass in true, you will get an IllegalStateException because the specified child already has a parent.
        final View view = inflater.inflate(R.layout.result_fragment7_layout, container, false);

        Log.d(TAG, "onCreateView: Started");

        //initializing variables
        btEnd = (Button) view.findViewById(R.id.btnConfirm6); //User has confirmed their answer
        btScore = (Button) view.findViewById(R.id.btnScore);

        //Display Score
        textView = (TextView)view.findViewById(R.id.scorecard);
        //result = ((CardquizActivity)getActivity().get();
        //textView.setText(result);

        btScore.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                result=((CardquizActivity)getActivity()).returnScore();
                String value = Integer.toString(result);
                textView.setText(value);
            }
        });

        //Confirm button Clicked
        btEnd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v2){
                ((CardquizActivity)getActivity()).setViewPager(0); //send back to master list..
                //grabScore();
            }
        });
        return view;
    }
}

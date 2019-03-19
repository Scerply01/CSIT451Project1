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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Fragment4 extends Fragment {
    private static final String TAG = "q3_Fragment 4";
    private static final int answer=3;

    //creating variables
    private Button btConfirm;
    private RadioGroup radioGroup;
    private RadioButton radioButton1, radioButton2, radioButton3;

    private static final int RB1_ID = 1;//first radio button id
    private static final int RB2_ID = 2;//second radio button id
    private static final int RB3_ID = 3;//third radio button id

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //When inflating and returning a Fragment’s View in onCreateView(), be sure to pass in false for attachToRoot.
        //If you pass in true, you will get an IllegalStateException because the specified child already has a parent.
        final View view = inflater.inflate(R.layout.q3_fragment4_layout, container, false);

        Log.d(TAG, "onCreateView: Started");

        //initializing variables
        btConfirm = (Button) view.findViewById(R.id.btnConfirm3); //User has confirmed their answer
        radioGroup = (RadioGroup) view.findViewById(R.id.radio_group3);
        radioButton1 = (RadioButton) view.findViewById(R.id.radio_g3_bt1);
        radioButton2 = (RadioButton) view.findViewById(R.id.radio_g3_bt2);
        radioButton3 = (RadioButton) view.findViewById(R.id.radio_g3_bt3);

        //set an id
        radioButton1.setId(RB1_ID);
        radioButton2.setId(RB2_ID);
        radioButton3.setId(RB3_ID);

        //Confirm onClick
        btConfirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v2){
                checkRadial();
            }
        });
        return view;
    }

    //Determine if answer is correct, or incorrect, if correct call addscore to increment score
    public void checkRadial (){
        //Data Validation, user selected nothing
        if(radioGroup.getCheckedRadioButtonId() == -1){
            Toast.makeText(getActivity(), "Select An Answer", Toast.LENGTH_SHORT).show();
        }else if(radioGroup.getCheckedRadioButtonId() == answer) { //Check if answer is correct
            ((CardquizActivity)getActivity()).addScore();
            Toast.makeText(getActivity(), "Correct", Toast.LENGTH_SHORT).show();
            //Store answer in DB
            ((CardquizActivity)getActivity()).setViewPager(4);
        }else{ //Answer is Wrong
            Toast.makeText(getActivity(), "Wrong Answer", Toast.LENGTH_SHORT).show();
            //Store answer in DB
            ((CardquizActivity)getActivity()).setViewPager(4);
        }
    }
}

package com.example.project1;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class Fragment6 extends Fragment {
    private static final String TAG = "q5_Fragment 6";

    //creating variables
    private Button btConfirm;
    private CheckBox checkBox1, checkBox2, checkBox3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //When inflating and returning a Fragment’s View in onCreateView(), be sure to pass in false for attachToRoot.
        //If you pass in true, you will get an IllegalStateException because the specified child already has a parent.
        final View view = inflater.inflate(R.layout.q5_fragment6_layout, container, false);

        Log.d(TAG, "onCreateView: Started");

        //initializing variables
        btConfirm = (Button) view.findViewById(R.id.btnConfirm5); //User has confirmed their answer
        checkBox1 = (CheckBox) view.findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) view.findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) view.findViewById(R.id.checkBox3);

        //Confirm onClick
        btConfirm.setOnClickListener(new View.OnClickListener(){
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View v2){
                checkCheckBox();
            }
        });
        return view;
    }

    //Determine if answer is correct, or incorrect, if correct call addscore to increment score
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void checkCheckBox (){
        //test data validation
        if(!checkBox1.isChecked() && !checkBox2.isChecked() && !checkBox3.isChecked()){
            Toast.makeText(getActivity(), "Select An Answer", Toast.LENGTH_SHORT).show();
        }else if(checkBox1.isChecked() || checkBox2.isChecked()){ //correct answer
            ((CardquizActivity)getActivity()).addScore();
            Toast.makeText(getActivity(), "Correct", Toast.LENGTH_SHORT).show();
            //Store game score
            ((CardquizActivity)getActivity()).addData();
            ((CardquizActivity)getActivity()).setViewPager(6); //send back to master list..
        }else{ //wrong answer
            Toast.makeText(getActivity(), "Correct", Toast.LENGTH_SHORT).show();
            //Store game score
            ((CardquizActivity)getActivity()).addData();
            ((CardquizActivity)getActivity()).setViewPager(6); //send back to master list..
        }
    }
}

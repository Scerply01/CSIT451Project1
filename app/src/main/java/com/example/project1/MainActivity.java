package com.example.project1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.lang.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating Variables
        Button logButton;
        Button regButton;

        //Initialize variables
        logButton = findViewById(R.id.loginBut);
        regButton = findViewById(R.id.registerBut);

        //set onclick to call view method
        logButton.setOnClickListener(new View.OnClickListener(){

            //create view method
            @Override
            public void onClick (View view){
                //call our method to open login form
                openLogin();
            }
        });

        regButton.setOnClickListener(new View.OnClickListener(){

            //create view method
            public void onClick (View view2){
                //call our method to open registration form
                openReg();
            }
        });
    }

    private void openLogin(){
        Intent showLog = new Intent(MainActivity.this, LoginActivity.class);
        MainActivity.this.startActivity(showLog);
    }

    private void openReg(){
        Intent showReg = new Intent (MainActivity.this, RegistrationActivity.class);
        MainActivity.this.startActivity(showReg);
    }
}
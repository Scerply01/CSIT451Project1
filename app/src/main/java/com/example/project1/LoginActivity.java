package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {
    //creating vars
    private EditText P_E_T_email, P_E_T_password;

    //create Object of DatabaseHelper
    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //creating var
        Button P_B_loginButton;

        //Initialize obj so it's not null
        mDatabaseHelper= new DatabaseHelper(this);

        //ini vars
        P_E_T_email = findViewById(R.id.email_login);
        P_E_T_password = findViewById(R.id.password_login);
        P_B_loginButton = findViewById(R.id.submitLogin);
        P_B_loginButton.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view) {
                log_in();
            }
        });
    }

    @SuppressWarnings("UnnecessaryReturnStatement")
    private void log_in() {

        //Create Vars & Grab Values
        String email = P_E_T_email.getText().toString();
        String password = P_E_T_password.getText().toString();

        String predefinedEmail = "demouser@msu.edu";
        String predefinedPassword = "demo";

        boolean cancel = false;

        //Resetting Errors
        P_E_T_email.setError(null);
        P_E_T_password.setError(null);

        //DataValidation
        if (TextUtils.isEmpty(email)){
            P_E_T_email.setError("Field is required");
            cancel = true;
        }

        if (TextUtils.isEmpty(password)){
            P_E_T_password.setError("Field is required");
            cancel = true;
        }

        //if cancel is true then data validation failed
        if (cancel){
            return;
        }else {
            //pass email and password values to checkCredentials Method
            checkCredentials (email, password);
            finish(); //kill activity
        }
    }

    public boolean checkCredentials (String email, String password) {
        //Toast messages
        String msg1 = "Success";
        String msg2 = "Login Failure";

        /*
         *if check is true then will allow login and new activity
         *Send email & password values to check against database
         */
        boolean check = mDatabaseHelper.checkData(email, password);

        //If passwords match then show success msg and open Cardquiz
        if (check) {
            toastMessage(msg1);
            openQuiz(email);
        }

        toastMessage(msg2);
        return false;
    }

    //Display Toast message
    private void toastMessage (String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    //Create intent with putExtra to pass along email of user signed in
    private void openQuiz(String email){
        Intent intent = new Intent(LoginActivity.this, CardquizActivity.class);
        intent.putExtra("USERNAME", email);
        LoginActivity.this.startActivity(intent);
    }
}
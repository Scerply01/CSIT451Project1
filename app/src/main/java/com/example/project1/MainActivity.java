package com.example.project1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.text.TextUtils;
import android.widget.EditText;
import java.lang.*;

public class MainActivity extends AppCompatActivity implements RegistrationActivity.DialogListener{
    //Creating Variables
    private Button logButton;
    private Button regButton;
    private EditText jemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize variables
        logButton = (Button)findViewById(R.id.loginBut);
        regButton = (Button)findViewById(R.id.registerBut);

        //set onclick to call view method
        logButton.setOnClickListener(new View.OnClickListener(){

            //create view method
            @Override
            public void onClick (View view){
                //call our method to open login form
                openLoginDialog();
            }
        });

        regButton.setOnClickListener(new View.OnClickListener(){

            //create view method
            public void onClick (View view2){
                //call our method to open registration form
                openRegDialog();
            }
        });
    }

    public void openLoginDialog(){
        LoginActivity dialog = new LoginActivity();
        dialog.show(getSupportFragmentManager(), "test");
    }

    public void openRegDialog(){
        RegistrationActivity dialog2 = new RegistrationActivity();
        dialog2.show(getSupportFragmentManager(), "test2");
    }


    /*
    Check values when attempting to register
     */
    public void checkReg(){

    }

    @Override
    public void passValues(String email) {
        //setContentView(R.layout.activity_registration); //this calls the reg layout butt removes us from dialogbox
        jemail = (EditText) findViewById(R.id.email);
        //jemail = email;


        //Var's to store values @ login
        //String eemail = jemail.getText().toString().trim();

        //Set ini value to null
        //jemail.setError(null);
/*
        boolean cancel = false;
        View focusView = null;

        //Check for a valid email address
        if (TextUtils.isEmpty(email)) {
            jemail.setError(getString(R.string.error_field_required));
            focusView= jemail;
            cancel = true;
        }

        if(cancel) { // cancel==true can just be 'cancel'
            focusView.requestFocus();
        }else {
            return;
        } */
        }
}



/*

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }



        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }




        //jemailView.setError(getString(R.string.error_field_require));
        //focusView = mEmailView;
 */
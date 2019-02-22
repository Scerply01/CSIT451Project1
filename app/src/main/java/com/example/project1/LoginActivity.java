package com.example.project1;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //creating var
        Button P_B_loginButton;

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
        //initializing new vars
        String email = P_E_T_email.getText().toString();
        String password = P_E_T_password.getText().toString();

        String predefinedEmail = "demouser@msu.edu";
        String predefinedPassword = "demo";

        boolean cancel = false;

        //Resetting Errors
        P_E_T_email.setError(null);
        P_E_T_password.setError(null);

        if (TextUtils.isEmpty(email)){
            P_E_T_email.setError("Field is required");
            cancel = true;
        }

        if (TextUtils.isEmpty(password)){
            P_E_T_password.setError("Field is required");
            cancel = true;
        }

        if (cancel){
            return;
        }else if(email.equals(predefinedEmail) && password.equals(predefinedPassword)){
            finish();
        }else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Invalid login. Please try again.",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
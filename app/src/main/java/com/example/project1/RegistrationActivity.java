package com.example.project1;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import java.lang.*;
import java.util.Calendar;

public class RegistrationActivity extends AppCompatActivity {
    //Creating vars to point to views
    private EditText P_E_T_fname, P_E_T_lname, P_E_T_email, P_E_T_password;
    private TextView P_T_V_dob;


    //Creating Listener used for DatePickerDialog
    private DatePickerDialog.OnDateSetListener myDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //creating var
        Button P_B_submit;

        //ini vars
        P_E_T_fname = findViewById(R.id.fname);
        P_E_T_lname = findViewById(R.id.lname);
        P_T_V_dob = findViewById(R.id.dob);
        P_E_T_email = findViewById(R.id.email);
        P_E_T_password = findViewById(R.id.password);
        P_B_submit = findViewById(R.id.submitRegisration);


        //When textView is pressed do:
        P_T_V_dob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Get year, month, day
                final Calendar cal= Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                //use DatePickerDialog class to create alert dialog
                //DatePickerDialog(Context context, android.R.style.theme, listener, year, month, day)
                DatePickerDialog dialog = new DatePickerDialog(RegistrationActivity.this,
                        android.R.style.Theme_Holo, //theme
                        myDateSetListener,
                        year,
                        month,
                        day);

                //No need for dialog window background behind calendar
                //noinspection ConstantConditions
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        //When date is selected do...
        myDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                //Incremenent month value by 1, java starts @ 0 for January
                month += 1;

                //Format appearance
                String date = month +"/" + day +"/" + year;

                //Display value
                P_T_V_dob.setText(date);
            }
        };
        P_B_submit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view2) {
                checkEmpty();
            }
        });
    }

    @SuppressWarnings("UnnecessaryReturnStatement")
    private void checkEmpty() {

        //Create Vars & Grab Values
        String fn = P_E_T_fname.getText().toString();
        String ln = P_E_T_lname.getText().toString();
        String dob = P_T_V_dob.getText().toString();
        String mail = P_E_T_email.getText().toString();
        String pw = P_E_T_password.getText().toString();

        //Resetting Errors
        P_E_T_fname.setError(null);
        P_E_T_lname.setError(null);
        P_T_V_dob.setError(null);
        P_E_T_email.setError(null);
        P_E_T_password.setError(null);

        //Set up exit var
        boolean cancel = false;

        //Check first name
        if(TextUtils.isEmpty(fn)){
            P_E_T_fname.setError("This field is required");
            cancel=true;
        } else if (fn.length()<3){
            P_E_T_fname.setError("Name is less than 3 characters");
        }

        //Check last name
        if(TextUtils.isEmpty(ln)){
            P_E_T_lname.setError("This field is required");
            cancel=true;
        } else if (ln.length()<3){
            P_E_T_lname.setError("Name is less than 3 characters");
            cancel=true;
        }

        //Check dob
        if(TextUtils.isEmpty(dob)){
            P_T_V_dob.setError("This field is required");
            cancel=true;
        }

        //Check email
        if(TextUtils.isEmpty(mail)){
            P_E_T_email.setError("This field is required");
            cancel=true;
        } else if (!isEmailValid(mail)) {
            P_E_T_email.setError("E-mail is invalid");
            cancel = true;
        }

        //Check password
        if(TextUtils.isEmpty(pw)){
            P_E_T_password.setError("This field is required");
            cancel=true;
        }else if (pw.length()<3){
            P_E_T_password.setError("Password is less than 3 characters");
            cancel=true;
        }
        //If data doesn't pass validation then exit method and show errors
        if (cancel){
            return;
        }
        else {
            finish(); //Exit Activity
        }
    }

    //check if email is valid
    private boolean isEmailValid(CharSequence mail) {
        return Patterns.EMAIL_ADDRESS.matcher(mail).matches();
    }
}

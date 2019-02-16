package com.example.project1;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.view.View;


public class RegistrationActivity extends AppCompatDialogFragment {
    private EditText et_fname, et_lname, et_dob, et_email, et_password;
    private DialogListener listener;
    private EditText f_email_box;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Using Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Inflating layout with "LayoutInflater"
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        //Creating view, saves me from writing inflater.inflate over and over
        View view = inflater.inflate(R.layout.activity_registration, null);

        //test, might not need this
        f_email_box = (EditText) view.findViewById(R.id.email);


        //Using Builder.setView for dialog construction. + creating title and action buttons
        builder.setView(view)
            .setTitle(R.string.dialogTitleReg)

            //POSITIVE Action Button, NOTE: This will only run AFTER clicking Submit button
            .setPositiveButton(R.string.positive, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    registerAttempt();

                    // When the user touches any of the action buttons created with an AlertDialog.Builder, the system dismisses the dialog for you.
                    // Possible solution: https://stackoverflow.com/questions/40261250/validation-on-edittext-in-alertdialog
                }
            })

            //NEGATIVE Action Button
            .setNegativeButton(R.string.negative, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Cancel Button Logic, Will dismiss Dialog
                }
            });

        //set views to corresponding editText box (fname, lname, dob, email, password)
        et_email = view.findViewById(R.id.email);
        //et_password = view.findViewById(R.id.password);

        //This command will create our dialog box
         return builder.create();
    }

    private void registerAttempt(){
        //Vars to store values
        String email = et_email.getText().toString();

        //CHANGES************************************************************
        boolean cancel = false;
        View focusView = null;

        //Check for a valid email address
        if (TextUtils.isEmpty(email)) {
            et_email.setError(getString(R.string.error_field_required));
            focusView= et_email;
            cancel = true;
        }

        if(cancel) { // cancel==true can just be 'cancel'
            focusView.requestFocus();
        }else {
            return;
        }
        //CHANGES END********************************************************



        //Passing values to host/MainActivity
        //listener.passValues(email);

    }

    public interface DialogListener{
        /*
         * Passing Events Back to the Dialog's Host
         * Doing this within DialogFragment
         */
        void passValues (String email);
    }

    @Override
    public void onAttach(Context context) {
        // Override the Fragment.onAttach() method to instantiate the DialogListener
        super.onAttach(context);

        // Verify host activity implements the callback interface
        try {
            // Instantiate DialogListener so we can send events to the host
            listener = (DialogListener) context; //Ctrl + alt + t will place line inside block of code
        } catch (ClassCastException e) {
            // If activity doesn't implement interface, throw exception
            throw new ClassCastException(context.toString()
                    + "must implement DialogListener"); //dev site has this as activity but had issues
        }

    }
}

/*
    Removed
    return super.onCreateDialog(savedInstanceState);


    Resources:
    https://developer.android.com/guide/topics/ui/dialogs
    https://developer.android.com/reference/android/support/v7/app/AppCompatDialog
    https://developer.android.com/reference/android/support/v7/app/AppCompatDialogFragment
    https://developer.android.com/guide/topics/ui/dialogs#java <--Dialog Builder Help



    BACKUP


                //POSITIVE Action Button, NOTE: This will only run AFTER clicking Submit button
            .setPositiveButton(R.string.positive, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Vars to store values
                    String email = et_email.getText().toString();

                    //CHANGES************************************************************
                    boolean cancel = false;
                    View focusView = null;

                    //Check for a valid email address
                    if (TextUtils.isEmpty(email)) {
                        et_email.setError(getString(R.string.error_field_required));
                        focusView= et_email;
                        cancel = true;
                    }

                    if(cancel) { // cancel==true can just be 'cancel'
                        focusView.requestFocus();
                    }else {
                        return;
                    }
                    //CHANGES END********************************************************



                    //Passing values to host/MainActivity
                    listener.passValues(email);

                }

 */
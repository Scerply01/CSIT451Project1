package com.example.project1;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;


public class LoginActivity extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Inflating layout with LayoutInflater
        LayoutInflater inflater = requireActivity().getLayoutInflater(); //could also use getActivity

        // Builder for dialog construction & passing null as its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.login_form, null))
            .setTitle(R.string.dialogTitle)

            //Creating Action Buttons
            .setPositiveButton(R.string.positive, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Submit Button Logic, Will dismiss Dialog
                }
            })
            .setNegativeButton(R.string.negative, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Cancel Button Logic, Will dismiss Dialog
                }
            });
         return builder.create();
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
 */
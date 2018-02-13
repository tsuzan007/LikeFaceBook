package com.example.macbookpro.MovieBook.ViewClass.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.macbookpro.likefacebook.R;


public class DialogFragment extends android.app.DialogFragment {

    public static DialogFragment newInstance(int title) {
        DialogFragment frag = new DialogFragment();
        Bundle args = new Bundle();
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.ic_lock_black_24dp)
                .setMessage(R.string.invalid_username)
                .setTitle("Message")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        }
                )
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        }
                )
                .create();
    }

}

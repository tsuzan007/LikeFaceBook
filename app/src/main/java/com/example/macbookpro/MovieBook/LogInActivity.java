package com.example.macbookpro.MovieBook;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import com.example.macbookpro.likefacebook.R;


public class LogInActivity extends Activity {
    protected Fragment loginfragment;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        loginfragment = getFragmentManager().findFragmentById(R.id.fragment1);

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//        myRef.setValue("Hello, World!");

    }
}

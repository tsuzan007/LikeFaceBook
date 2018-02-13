package com.example.macbookpro.MovieBook.ViewClass.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macbookpro.MovieBook.MainActivity;
import com.example.macbookpro.MovieBook.Presenter.MainPresenter;
import com.example.macbookpro.likefacebook.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;


public class LoginFragment extends Fragment {

    @BindView(R.id.edittext_username)
    EditText username;
    @BindView(R.id.edittext_password)
    EditText password;
    @BindView(R.id.button_signin)
    Button signin;
    @BindView(R.id.button_facebooklogin)
    LoginButton facebooklogin;
    @BindView(R.id.textview_signup)
    TextView signup;
    FirebaseAuth firebaseAuth;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private CallbackManager callbackManager;
    private MainPresenter mainPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainPresenter = new MainPresenter();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getActivity());
//        firebaseAuth=FirebaseAuth.getInstance();
//        FirebaseCrash.report(new Exception("My first Android non-fatal error"));
//        FirebaseCrash.log("MainActivity started");

    }

    @Override
    public void onStart() {
        super.onStart();
        // FirebaseUser currentUser=firebaseAuth.getCurrentUser();
        sharedPreferences = getActivity().getSharedPreferences("userstatus", MODE_PRIVATE);
        if (sharedPreferences.getBoolean("userStatus", false)) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);

        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }



    /*
        Code to get hash key
        --------------------
        try {
            PackageInfo info = getActivity().getPackageManager().getPackageInfo(
                    "com.sujan.mykitaab",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash........:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login, container, false);
        ButterKnife.bind(this, view);
        facebooklogin.setReadPermissions(new String[]{"public_profile", "user_birthday", "email", "user_posts", "user_friends", "read_custom_friendlists"});
        facebooklogin.setFragment(this);
        callbackManager = CallbackManager.Factory.create();
        return view;
    }


    @OnClick(R.id.button_facebooklogin)
    public void onClickbutton(View view) {
        if (view.getId() == R.id.button_facebooklogin) {
            facebooklogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    mainPresenter.onSuccess(loginResult);

                }

                @Override
                public void onCancel() {
                    Toast.makeText(getActivity(), "Log in cancelled.", Toast.LENGTH_SHORT).show();


                }

                @Override
                public void onError(FacebookException error) {
                    Toast.makeText(getActivity(), "Unable to sign in! Please Retry.", Toast.LENGTH_SHORT).show();
                }
            });


        } else if (view.getId() == R.id.button_signin) {
            String name = username.getText().toString();
            if (!TextUtils.isEmpty(name)) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("username", name);
                startActivityForResult(intent, 1);

            } else {
                DialogFragment dialogFragment = new DialogFragment();
                dialogFragment.show(getFragmentManager(), "message dialog");
            }


        }
    }

    @OnClick(R.id.button_signin)
    public void onSignInClicked(View view) {
        firebaseAuth.signInWithEmailAndPassword("tsuzan007@mykitab.com", "Hello123")
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("...", "signInWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("...", "signInWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });


    }

    @OnClick(R.id.textview_signup)
    public void onSignupClicked(View view) {
        /*
        get userid, name, email address from user and pass it to the function
         */
        mainPresenter.onSignupClicked();
        firebaseAuth.createUserWithEmailAndPassword("tsuzan007@mykitab.com", "Hello123")
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("...", "createUserWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("...", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //  updateUI(null);
                        }


                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


}

package com.donnicholaus.unipals;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.donnicholaus.unipals.R;
import com.donnicholaus.unipals.home.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Context mContext;

    private LottieAnimationView animationView;
    private LinearLayout greyedLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mContext = Login.this;
        greyedLinearLayout = findViewById(R.id.greyedBg);
        animationView = findViewById(R.id.animatedDialog);
        animationView.setVisibility(View.GONE);

        init();

//        animationView.addAnimatorListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animator) {
//                animationView.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animator) {
//
//            }
//        });

    }

    private boolean isNull(String string){
        return string.equals("");
    }


    private  void init(){

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //Initialize the link word to register activity
        Button registerTxt = findViewById(R.id.txtRegister);
        Button loginBtn = findViewById(R.id.btnLogin);

        final EditText usernameTxt = findViewById(R.id.edtEmail);
        final EditText passwordTxt = findViewById(R.id.edtPassword);


        registerTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Initialize the login button, And all Other fields
                final String username = usernameTxt.getText().toString();
                final String password = passwordTxt.getText().toString();

                if(isNull(username) || isNull(password)){
                    Toast.makeText(getApplicationContext(), "All Fields are Required", Toast.LENGTH_SHORT).show();
                }else{
                    Log.d("TAG", "init: " + username + password);
                    greyedLinearLayout.setVisibility(View.VISIBLE);
                    animationView.setVisibility(View.VISIBLE);
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    mAuth.signInWithEmailAndPassword(username, password)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("SIgnIn", "signInWithEmail:success");
                                        Toast.makeText(Login.this, "Login successful.",
                                                Toast.LENGTH_SHORT).show();
                                        animationView.setVisibility(View.GONE);
                                        greyedLinearLayout.setVisibility(View.GONE);
                                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("Msg", "signInWithEmail:failure", task.getException());
                                        Toast.makeText(Login.this, "Invalid Credentials .",
                                                Toast.LENGTH_SHORT).show();
                                        animationView.setVisibility(View.GONE);
                                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                        greyedLinearLayout.setVisibility(View.GONE);
                                        updateUI(null);
                                    }

                                    // ...
                                }
                            });


                }

            }
        });

    }

    //Change UI according to user data.
    public void  updateUI(FirebaseUser account){
        if(account != null) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }

}























//package com.donnicholaus.instapals;
//
//import androidx.appcompat.app.ActionBar;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.view.View;
//import android.view.animation.AlphaAnimation;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.FrameLayout;
//import android.widget.Toast;
//
//import com.donnicholaus.unipals.home.HomeActivity;
//import com.parse.LogInCallback;
//import com.parse.ParseAnalytics;
//import com.parse.ParseException;
//import com.parse.ParseUser;
//
//import java.util.Objects;
//
//public class Login extends AppCompatActivity{
//
//    Button loginBtn;
//
//    AlphaAnimation inAnimation;
//    AlphaAnimation outAnimation;
//
//    FrameLayout progressBarHolder;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.appbar_layout);
//
//        loginBtn = findViewById(R.id.btnLogin);
//        progressBarHolder = findViewById(R.id.progressBarHolder);
//
//        SetupParseUser();
//
//        ParseAnalytics.trackAppOpenedInBackground(getIntent());
//    }
//
//    private  void init(){
//
//        //Initialize the link word to register activity
//        Button registerTxt = findViewById(R.id.txtRegister);
//        Button help = findViewById(R.id.help);
//
//        registerTxt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Login.this, Register.class);
//                startActivity(intent);
//            }
//        });
//
//        help.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Login.this, Loading.class);
//                startActivity(intent);
//            }
//        });
//
//        //Initialize the login button, And all Other fields
//        loginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final EditText usernameTxt = findViewById(R.id.edtEmail);
//                final EditText passwordTxt = findViewById(R.id.edtPassword);
//
//                final String username = usernameTxt.getText().toString();
//                final String password = passwordTxt.getText().toString();
//
//                new MyTask().execute(username,password);
//
//            }
//        });
//
//
//    }
//
//    private  void SetupParseUser(){
//
//        ParseUser currentUser = ParseUser.getCurrentUser();
//        if(currentUser != null){
//            Intent intent = new Intent(Login.this, HomeActivity.class);
//            startActivity(intent);
//            finish();
//        }else init();
//
//    }
//
//
////    public void allFieldsAreRequired (){
////        Toast.makeText(getApplicationContext(),"Username and Password Required", Toast.LENGTH_SHORT).show();
////    }
//
//    Boolean success = false;
//    String error = "";
//    private class MyTask extends AsyncTask<String, Void, Boolean> {
//
//        @Override
//        protected Boolean doInBackground(String... strings) {
//
//            if(strings[0].equals("") || strings[1].equals("")){
//                success = false;
//            }else {
//
//                /**
//                 *If The User Used Email intead of usernmane use rthis code here
//                 * // If the entered username has an @, assume it is an email
//                 * String username = "bob@example.com";
//                 *
//                 * if (username.indexOf("@")) {
//                 *   ParseQuery<ParseUser> query = ParseUser.getQuery();
//                 *   query.whereEqualTo("email", username);
//                 *   query.getFirstInBackground(new GetCallback<ParseObject>() {
//                 *       public void done(ParseObject object, ParseException e) {
//                 *           if (object == null) {
//                 *               Log.d("score", "The getFirst request failed.");
//                 *           } else {
//                 *                String actualUsername = object.get("username");
//                 *                ParseUser.logInInBackground(actualusername, "showmethemoney", new LogInCallback() {
//                 *                  ...
//                 *                });
//                 *           }
//                 *       }
//                 *    });
//                 *
//                 */
//
//                ParseUser.logInInBackground(strings[0], strings[1], new LogInCallback() {
//                    @Override
//                    public void done(ParseUser user, ParseException e) {
//                        if (user != null){
//                            success = true;
//                        }else {
//                            error = e.getMessage();
//                            //Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//
//            }
//
//            return success;
//        }
//
//        @Override
//        protected void onPostExecute(Boolean b) {
//            if (!success){
//                Toast.makeText(getApplicationContext(), "All Fields are Required ", Toast.LENGTH_SHORT).show();
//            }else if (!error.equals("")){
//                Toast.makeText(getApplicationContext(),error, Toast.LENGTH_SHORT).show();
//            }else{
//                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(Login.this, HomeActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        }
//
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//            super.onProgressUpdate(values);
//        }
//
//
//    }
//}
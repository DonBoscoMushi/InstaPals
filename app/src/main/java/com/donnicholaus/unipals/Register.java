package com.donnicholaus.unipals;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.donnicholaus.unipals.util.FirebaseMethods;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseMethods firebaseMethods;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;


    private LinearLayout greyedLinearLayout;
    private LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseMethods = new FirebaseMethods(Register.this);
        greyedLinearLayout = findViewById(R.id.greyedBg);
        animationView = findViewById(R.id.animatedDialog);
        animationView.setVisibility(View.GONE);

        mAuth = FirebaseAuth.getInstance();

        Button loginToRegister = findViewById(R.id.txtLogin);
        Button register = findViewById(R.id.btn_register);
        final EditText emailTxt = findViewById(R.id.input_email);
        final EditText usernameTxt = findViewById(R.id.input_username);
        final EditText fullnameTxt = findViewById(R.id.input_fullname);
        final EditText passwordTxt = findViewById(R.id.input_password);

        firebaseSetup();



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailTxt.getText().toString();
                String username = usernameTxt.getText().toString();
                String fullname = fullnameTxt.getText().toString();
                String password = passwordTxt.getText().toString();

                if(email.equals("") || username.equals("") || fullname.equals("") || password.equals("")){
                    Toast.makeText(getApplicationContext(), "All Fields are required", Toast.LENGTH_SHORT).show();
                }else {
                    animationView.setVisibility(View.VISIBLE);
                    greyedLinearLayout.setVisibility(View.VISIBLE);

                    firebaseMethods.registerNewUser(email, username, fullname, password);

//                    animationView.setVisibility(View.GONE);
//                    greyedLinearLayout.setVisibility(View.GONE);
                }
            }
        });

        loginToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

    }


    private void firebaseSetup(){
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser =     firebaseAuth.getCurrentUser();

                if (firebaseUser != null){
                    //User found

                    mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            //Check username if exists


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }else{

                }
            }
        };

    }

//    private class MyTask extends AsyncTask<String, Void, Boolean>{
//
//
//        @Override
//        protected Boolean doInBackground(String... strings) {
//            return null;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected void onPostExecute(Boolean aBoolean) {
//            super.onPostExecute(aBoolean);
//        }
//    }
}
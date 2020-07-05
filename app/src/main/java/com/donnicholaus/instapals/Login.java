package com.donnicholaus.instapals;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.donnicholaus.instapals.home.Home;
import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.Objects;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.appbar_layout);

        SetupParseUser();
        init();


        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }

    private  void init(){

        //Initialize the link word to register activity
        Button registerTxt = findViewById(R.id.txtRegister);

        registerTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        //Initialize the login button, And all Other fields
        Button loginBtn = findViewById(R.id.btnLogin);
        final EditText usernameTxt = findViewById(R.id.edtEmail);
        final EditText passwordTxt = findViewById(R.id.edtPassword);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String username = usernameTxt.getText().toString();
                final String password = passwordTxt.getText().toString();

                if(username.equals("") || password.equals("")){
                    Toast.makeText(getApplicationContext(),"Username and Password Required", Toast.LENGTH_SHORT).show();
                }else {

                    /**
                     *If The User Used Email intead of usernmane use rthis code here
                     * // If the entered username has an @, assume it is an email
                     * String username = "bob@example.com";
                     *
                     * if (username.indexOf("@")) {
                     *   ParseQuery<ParseUser> query = ParseUser.getQuery();
                     *   query.whereEqualTo("email", username);
                     *   query.getFirstInBackground(new GetCallback<ParseObject>() {
                     *       public void done(ParseObject object, ParseException e) {
                     *           if (object == null) {
                     *               Log.d("score", "The getFirst request failed.");
                     *           } else {
                     *                String actualUsername = object.get("username");
                     *                ParseUser.logInInBackground(actualusername, "showmethemoney", new LogInCallback() {
                     *                  ...
                     *                });
                     *           }
                     *       }
                     *    });
                     *
                     */


                    ParseUser.logInInBackground(username, password, new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (user != null){
                                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Login.this, Home.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }


            }
        });

    }

    private  void SetupParseUser(){

    }

}
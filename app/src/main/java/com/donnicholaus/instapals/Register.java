package com.donnicholaus.instapals;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button register = findViewById(R.id.btn_register);
        final EditText emailTxt = findViewById(R.id.input_email);
        final EditText usernameTxt = findViewById(R.id.input_username);
        final EditText fullnameTxt = findViewById(R.id.input_fullname);
        final EditText passwordTxt = findViewById(R.id.input_password);

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
                    ParseUser user = new ParseUser();

                    user.setUsername(username);
                    user.setPassword(password);
                    user.setEmail(email);
                    user.put("Fullname", fullname);

                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null){
                                Toast.makeText(getApplicationContext(), "Sign up Successful.", Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(), "Login to Continue.", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(Register.this, Login.class);
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
}
package com.donnicholaus.instapals.home;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.donnicholaus.instapals.Login;
import com.donnicholaus.instapals.R;
import com.google.android.material.snackbar.Snackbar;
import com.parse.ParseUser;

import java.util.Objects;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.appbar_layout);

        Button logoutBtn = findViewById(R.id.btnLogout);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOut();
                Intent intent = new Intent(Home.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        ImageButton icon_profile = findViewById(R.id.icon_profile);
        ImageButton icon_message = findViewById(R.id.action_message);
        TextView username = findViewById(R.id.usernameTxt);

        username.setText(ParseUser.getCurrentUser().getUsername());

        icon_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Its Working", Toast.LENGTH_SHORT).show();
            }
        });

        icon_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "You are really a man", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

}
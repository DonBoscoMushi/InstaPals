package com.donnicholaus.instapals.profile;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.donnicholaus.instapals.R;
import com.donnicholaus.instapals.util.BottomNavHelper;
import com.donnicholaus.instapals.util.UniversalImageLoader;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ProfileActivity extends AppCompatActivity {

    private ImageView imageView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        progressBar = findViewById(R.id.progressDialog);
        progressBar.setVisibility(View.GONE);
        imageView = findViewById(R.id.profileImg);

        initImageLoader();
        setupBottomNav();
        setupToolBar();
        setProfileImage();
    }

    private void setupToolBar(){
        Toolbar toolbar = findViewById(R.id.profileToolbar);
        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch(item.getItemId()){

                    case R.id.profileMenu:
                        Toast.makeText(getApplicationContext(), "profile", Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    public void setupBottomNav(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        BottomNavHelper.enableNavigation(ProfileActivity.this, bottomNavigationView);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);
    }

    private void initImageLoader(){
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(ProfileActivity.this);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }


    private void setProfileImage(){
            String imgURL = "homepages.cae.wisc.edu/~ece533/images/arctichare.png";
            UniversalImageLoader.setImage(imgURL, imageView, progressBar, "https://");
    }

}

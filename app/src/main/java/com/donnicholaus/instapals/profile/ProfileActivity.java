package com.donnicholaus.instapals.profile;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.donnicholaus.instapals.R;
import com.donnicholaus.instapals.util.BottomNavHelper;
import com.donnicholaus.instapals.util.GridImageAdapter;
import com.donnicholaus.instapals.util.UniversalImageLoader;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private ImageView imageView;
    private ProgressBar progressBar;
    private Context mContext = ProfileActivity.this;

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

        tempGrid();
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


    private void setImageGrid(ArrayList<String> imgURLs){
        GridView gridView = findViewById(R.id.gridView);
        GridImageAdapter gridImageAdapter = new GridImageAdapter(mContext, R.layout.layout_grid_imageview, "", imgURLs);
        gridView.setAdapter(gridImageAdapter);
    }

    private void tempGrid(){
        ArrayList<String> imgURLs = new ArrayList<>();
        imgURLs.add("https://homepages.cae.wisc.edu/~ece533/images/airplane.png");
        imgURLs.add("https://homepages.cae.wisc.edu/~ece533/images/arctichare.png");
        imgURLs.add("https://homepages.cae.wisc.edu/~ece533/images/baboon.png");
        imgURLs.add("https://homepages.cae.wisc.edu/~ece533/images/barbara.png");
        imgURLs.add("https://homepages.cae.wisc.edu/~ece533/images/boat.png");
        imgURLs.add("https://homepages.cae.wisc.edu/~ece533/images/cat.png");
        imgURLs.add("https://homepages.cae.wisc.edu/~ece533/images/fruits.png");
        imgURLs.add("https://homepages.cae.wisc.edu/~ece533/images/girl.png");
        imgURLs.add("https://homepages.cae.wisc.edu/~ece533/images/goldhill.png");
        imgURLs.add("https://homepages.cae.wisc.edu/~ece533/images/lena.png");
        imgURLs.add("https://homepages.cae.wisc.edu/~ece533/images/monarch.png");
        imgURLs.add("https://homepages.cae.wisc.edu/~ece533/images/mountain.png");
        imgURLs.add("https://homepages.cae.wisc.edu/~ece533/images/peppers.png");
        imgURLs.add("https://homepages.cae.wisc.edu/~ece533/images/pool.png");
        imgURLs.add("https://homepages.cae.wisc.edu/~ece533/images/sails.png");
        imgURLs.add("https://homepages.cae.wisc.edu/~ece533/images/serrano.png");
        imgURLs.add("https://homepages.cae.wisc.edu/~ece533/images/tulips.png");
        imgURLs.add("https://homepages.cae.wisc.edu/~ece533/images/watch.png");
        imgURLs.add("https://homepages.cae.wisc.edu/~ece533/images/zelda.png");

        setImageGrid(imgURLs);
    }
}

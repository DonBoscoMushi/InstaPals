package com.donnicholaus.unipals.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.donnicholaus.unipals.Login;
import com.donnicholaus.unipals.R;
import com.donnicholaus.unipals.util.BottomNavHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupBottomNav();
        setupViewPager();

        mAuth = FirebaseAuth.getInstance();

    }

    // ************************** Firebase Code ***********************************

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    //Change UI according to user data.
    public void  updateUI(FirebaseUser account){
        if(account == null) {
            startActivity(new Intent(this, Login.class));
        }
    }




    public void setupBottomNav(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        BottomNavHelper.enableNavigation(HomeActivity.this, bottomNavigationView);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
    }

    /**
     *
     * Add the three tabs in home activity
     */
    private void setupViewPager(){
        SectionPagerAdapter sectionPagerAdopter = new SectionPagerAdapter(getSupportFragmentManager(), 0);
        sectionPagerAdopter.addFragment(new CameraFragment());
        sectionPagerAdopter.addFragment(new HomeFragment());
        sectionPagerAdopter.addFragment(new MessageFragment());

        ViewPager viewPager = findViewById(R.id.container);
        viewPager.setAdapter(sectionPagerAdopter);

        TabLayout tabLayout = findViewById(R.id.topTabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);
        tabLayout.getTabAt(1).setText(R.string.app_name);
        tabLayout.getTabAt( 2).setIcon(R.drawable.ic_send);
    }

}
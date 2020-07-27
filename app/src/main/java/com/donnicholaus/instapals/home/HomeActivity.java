package com.donnicholaus.instapals.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.donnicholaus.instapals.R;
import com.donnicholaus.instapals.util.BottomNavHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupBottomNav();
        setupViewPager();
//        Button logoutBtn = findViewById(R.id.btnLogout);
//        logoutBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ParseUser.logOut();
//                Intent intent = new Intent(HomeActivity.this, Login.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//        ImageButton icon_profile = findViewById(R.id.icon_profile);
//        ImageButton icon_message = findViewById(R.id.action_message);
//        TextView username = findViewById(R.id.usernameTxt);
//
//        username.setText(ParseUser.getCurrentUser().getUsername());
//
//        icon_message.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "Its Working", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        icon_profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "You are really a man", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

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
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_send);
    }

}
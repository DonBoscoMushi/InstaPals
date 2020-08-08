package com.donnicholaus.unipals.util;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.donnicholaus.unipals.R;
import com.donnicholaus.unipals.home.HomeActivity;
import com.donnicholaus.unipals.profile.ProfileActivity;
import com.donnicholaus.unipals.search.SearchActivity;
import com.donnicholaus.unipals.share.ShareActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavHelper {

    public static void enableNavigation(final Context context, BottomNavigationView bottomNavigationView){
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.ic_home:
                        Intent intent1 = new Intent(context, HomeActivity.class);
                        context.startActivity(intent1);
                        break;

                    case R.id.ic_search:
                        Intent intent2 = new Intent(context, SearchActivity.class);
                        context.startActivity(intent2);
                        break;

                    case R.id.ic_add:
                        Intent intent3 = new Intent(context, ShareActivity.class);
                        context.startActivity(intent3);
                        break;

                    case R.id.ic_profile:
                        Intent intent4 = new Intent(context, ProfileActivity.class);
                        context.startActivity(intent4);
                        break;
                }

                return false;
            }
        });
    }
}

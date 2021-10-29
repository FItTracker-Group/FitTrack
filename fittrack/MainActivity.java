package com.codepath.fittrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.codepath.fittrack.fragments.FeedFragment;
import com.codepath.fittrack.fragments.HomeFragment;
import com.codepath.fittrack.fragments.MealFragment;
import com.codepath.fittrack.fragments.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        fragment = new HomeFragment();
//                        Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_meal:
                        fragment = new MealFragment();
//                        Toast.makeText(MainActivity.this, "Meal", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_feed:
                        fragment = new FeedFragment();
//                        Toast.makeText(MainActivity.this, "Feed", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_user:
                    default:
                        fragment = new UserFragment();
//                        Toast.makeText(MainActivity.this, "User", Toast.LENGTH_SHORT).show();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.nav_home);


    }

}
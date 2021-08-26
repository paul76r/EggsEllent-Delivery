package com.example.eggdelivery.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.eggdelivery.Adapter.Adapter;
import com.example.eggdelivery.Fragments.FragmentMain;
import com.example.eggdelivery.Fragments.FragmentOrder;
import com.example.eggdelivery.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView nav_view;
    ImageView menu;
    RelativeLayout content;

    private PagerAdapter mPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.eggsy);

        drawerLayout = findViewById(R.id.drawer_layout);
        nav_view = findViewById(R.id.navigation_view);
        menu = findViewById(R.id.imageView);
        content = findViewById(R.id.content);

        navigationDrawer();

        changeLanguage();

        mPagerAdapter = new Adapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);

        setupViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager mViewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentMain(), "Main");
        adapter.addFragment(new FragmentOrder(), "Main");

        mViewPager.setAdapter(adapter);
    }

    private void navigationDrawer() {
        nav_view.bringToFront();
        nav_view.setNavigationItemSelectedListener(this);
        nav_view.setCheckedItem(R.id.nav_home);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.changelanguage:
                showChangeLanguageDialog();
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                break;
        }

        return true;
    }

    private void showChangeLanguageDialog() {
        final String[] langs = {"Arabic", "English", "French"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        mBuilder.setTitle("Select Language");
        mBuilder.setSingleChoiceItems(langs, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int xer) {
                if (xer == 0) {
                    setLocale("ar");
                    recreate();
                } else if (xer == 1) {
                    setLocale("en");
                    recreate();
                } else if (xer == 2) {
                    setLocale("fr");
                    recreate();
                }
                dialog.dismiss();
            }
        });
        AlertDialog mDialog = mBuilder.create();
        mBuilder.show();
    }

    private void setLocale(String language) {
        Locale locale = new Locale(language);
        locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = getSharedPreferences("paul", MODE_PRIVATE).edit();
        editor.putString("tpaul", language);
        editor.apply();
    }

    public void changeLanguage() {
        SharedPreferences preferences = getSharedPreferences("paul", Activity.MODE_PRIVATE);
        String lang = preferences.getString("tpaul", "");
        setLocale(lang);
    }


    public void setViewPager(int i) {
        mViewPager.setCurrentItem(i);
    }
}

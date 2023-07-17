package com.example.bottomnavigationwithdrawermenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.bottomnavigationwithdrawermenu.Fragment.BlockDiversionFragment;
import com.example.bottomnavigationwithdrawermenu.Fragment.BlockListFragment;
import com.example.bottomnavigationwithdrawermenu.Fragment.FAQFragment;
import com.example.bottomnavigationwithdrawermenu.Fragment.HelpFragment;
import com.example.bottomnavigationwithdrawermenu.Fragment.HomeFragment;
import com.example.bottomnavigationwithdrawermenu.Fragment.NotificationFragment;
import com.example.bottomnavigationwithdrawermenu.Fragment.PremiumFragment;
import com.example.bottomnavigationwithdrawermenu.Fragment.SettingsFragment;
import com.example.bottomnavigationwithdrawermenu.Fragment.SupportFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    FloatingActionButton fab;



    // bottom navigation
    private enum BottomNavigationFragment {
        HOME(R.id.nav_home, new HomeFragment()),
        BLOCK_LIST(R.id.nav_block_list, new BlockListFragment()),
        HELP(R.id.nav_help, new HelpFragment()),
        PREMIUM(R.id.nav_bottom_premium, new PremiumFragment());

        private final int itemId;
        private final Fragment fragment;

        BottomNavigationFragment(int itemId, Fragment fragment) {
            this.itemId = itemId;
            this.fragment = fragment;
        }


        public int getItemId() {
            return itemId;
        }

        public Fragment getFragment() {
            return fragment;
        }

        public static BottomNavigationFragment fromItemId(int itemId) {
            for (BottomNavigationFragment fragment : values()) {
                if (fragment.itemId == itemId) {
                    return fragment;
                }
            }
            throw new IllegalArgumentException("Invalid navigation item id");
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);


        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // DrawerLayout
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // NavigationView
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottomNavigationView);


        // Set default fragment to home fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, BottomNavigationFragment.HOME.getFragment())
                .commit();

        // Setup bottom navigation
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            BottomNavigationFragment selectedFragment = BottomNavigationFragment.fromItemId(item.getItemId());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment.getFragment())
                    .commit();
            return true;
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomDialog();
            }
        });

    }



    // clickListener for drawer menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);

        MenuItemEnum menuItemEnum = null;

        if (item.getItemId() == R.id.nav_dis) {
            menuItemEnum = MenuItemEnum.BLOCK_DIVERSION;
        }else if (item.getItemId() == R.id.nav_noti) {
            menuItemEnum = MenuItemEnum.NOTIFICATION;
        }else if (item.getItemId() == R.id.nav_support) {
            menuItemEnum = MenuItemEnum.SUPPORT;
        }else if (item.getItemId() == R.id.nav_Premium) {
            menuItemEnum = MenuItemEnum.PREMIUM;
        }else if (item.getItemId() == R.id.nav_faq) {
            menuItemEnum = MenuItemEnum.FAQ;
        }else if (item.getItemId() == R.id.nav_setting) {
            menuItemEnum = MenuItemEnum.SETTINGS;
        }

        if (menuItemEnum != null) {
            Fragment fragment = null;

            switch (menuItemEnum) {
                case BLOCK_DIVERSION:
                    fragment = new BlockDiversionFragment();
                    break;
                case NOTIFICATION:
                    fragment = new NotificationFragment();
                    break;
                case SUPPORT:
                    fragment = new SupportFragment();
                    break;
                case PREMIUM:
                    fragment = new PremiumFragment();
                    break;
                case FAQ:
                    fragment = new FAQFragment();
                    break;
                case SETTINGS:
                    fragment = new SettingsFragment();
                    break;
            }
            if (fragment != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
                return true;
            }
        }

        return false;

    }


    // clickListener for drawer menu
    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            BottomNavigationFragment navItem = BottomNavigationFragment.fromItemId(item.getItemId());

            if (navItem != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, navItem.getFragment())
                        .commit();
                return true;
            }
            return false;
        }
    };


    // enum for drawer menu
    private enum MenuItemEnum {
        BLOCK_DIVERSION,
        NOTIFICATION,
        SUPPORT,
        PREMIUM,
        FAQ,
        SETTINGS,

    }

    private void showBottomDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        LinearLayout videoLayout = dialog.findViewById(R.id.layoutVideo);
        LinearLayout shortsLayout = dialog.findViewById(R.id.layoutShorts);
        LinearLayout liveLayout = dialog.findViewById(R.id.layoutLive);
        ImageView cancelButton = dialog.findViewById(R.id.cancelButton);

        videoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(MainActivity.this,"Upload a Video is clicked",Toast.LENGTH_SHORT).show();

            }
        });

        shortsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(MainActivity.this,"Create a short is Clicked",Toast.LENGTH_SHORT).show();

            }
        });

        liveLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(MainActivity.this,"Go live is Clicked",Toast.LENGTH_SHORT).show();

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }

}
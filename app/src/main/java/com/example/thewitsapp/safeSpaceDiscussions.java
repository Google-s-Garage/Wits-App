package com.example.thewitsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.ContentValues;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class safeSpaceDiscussions extends AppCompatActivity {
//dynamically add tab items
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private LinearLayout linearLayout;

    //declare fragments
    private MyDiscussions myDiscussions;
    private ActiveDiscussions activeDiscussions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_space_discussions);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        myDiscussions = new MyDiscussions();
        activeDiscussions = new ActiveDiscussions();

        tabLayout.setupWithViewPager(viewPager);

        viewPagerAdapter viewPagerAdapter = new viewPagerAdapter(getSupportFragmentManager(),0);
        viewPagerAdapter.addFragment(myDiscussions, "My Discussions");
        viewPagerAdapter.addFragment(activeDiscussions, "Active Discussions");
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_forum_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_person_outline_black_24dp);

    }

    private class viewPagerAdapter extends FragmentPagerAdapter {


        private List<Fragment> fragments = new ArrayList<>();
        private List<String> fragmentTitle = new ArrayList<>();


        public viewPagerAdapter(@NonNull FragmentManager fm, int behavior) {

            super(fm, behavior);
        }

        public void addFragment(Fragment fragment, String title){
            fragments.add(fragment);
            fragmentTitle.add(title);

        }

        @NonNull
        @Override
        public Fragment getItem(int position) {

            return fragments.get(position);
        }

        @Override
        public int getCount() {

            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            return fragmentTitle.get(position);
        }


    }
}

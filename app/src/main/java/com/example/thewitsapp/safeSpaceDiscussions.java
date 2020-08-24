package com.example.thewitsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentValues;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class safeSpaceDiscussions extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private LinearLayout linearLayout;

    //declare fragments
    private MyDiscussions myDiscussions;
    private ActiveDiscussions activeDiscussions;

    public FloatingActionButton addDiscCommentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_space_discussions);
        init();
    }

    public void init(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addDiscCommentBtn = findViewById(R.id.add_discussion);
        addDiscCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Let's go
                final Dialog commentDialog = new Dialog(safeSpaceDiscussions.this);

                //The resource layout that we gonna use as the pop alert box
                //
                commentDialog.setContentView(R.layout.safespace_dialog_pop_up);

                //our views on the dialog
                ImageView cancelImage = commentDialog.findViewById(R.id.dialog_cancel_button);
                final EditText editComment = commentDialog.findViewById(R.id.dialog_comment);
                Button postButton = commentDialog.findViewById(R.id.safeSpace_dialog_button);

                cancelImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //close the dialog
                        commentDialog.dismiss();
                    }
                });

                //When clicking this the comment must be taken to the database
                postButton.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("StaticFieldLeak")
                    @Override
                    public void onClick(View v) {


                        if(TextUtils.isEmpty(editComment.getText().toString().trim())) editComment.setError("No Comment");
                            //There is some text in the comment bar
                        else{

                            String comment = editComment.getText().toString().trim();

                            ContentValues params = new ContentValues();
                            params.put("USER_ID",MainActivity.userID);
                            params.put("SAFE_NAME",MainActivity.safeName);
                            params.put("SAFE_MSG",comment);


                            new ServerCommunicator("https://lamp.ms.wits.ac.za/~s1872817/postSafeSpaceMsg.php", params) {
                                @Override
                                protected void onPostExecute(String output) {

                                    if(output.equals("1")){

                                        Toast.makeText(safeSpaceDiscussions.this,"Discussion Started",Toast.LENGTH_SHORT).show();
                                        init();
                                        editComment.setText("");
                                    }

                                    else{

                                        Toast.makeText(safeSpaceDiscussions.this,"Couldn't Comment",Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }.execute();
                        }
                    }
                });

                commentDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                commentDialog.show();
            }
        });

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

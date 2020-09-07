package com.example.thewitsapp;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class photographyActivityTest {

    @Rule
    public ActivityTestRule<photographyActivity> photographyActivityActivityTestRule = new ActivityTestRule<>(photographyActivity.class);

    @Test
    public void clickFloatBttn(){
        onView(withId(R.id.add_photography_post)).perform(click());
    }
}
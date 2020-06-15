package com.example.thewitsapp;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class creativesActivityTest {

    @Rule
    public ActivityTestRule<creativesActivity> creativesActivityActivityTestRule = new ActivityTestRule<>(creativesActivity.class);

    @Test
    public void clickedBotton(){
        onView(withId(R.id.photograpy)).perform(click());
        onView(withId(R.id.add_photography_post)).perform(click());
        onView(withId(R.id.photography_caption)).perform(typeText("Im about to write a caption"));
        onView(withId(R.id.photography_flaot_caption)).perform(click());


    }
}
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
public class AccomodationMainTest {

    @Rule
    public ActivityTestRule<AccomodationMain> accomodationMainActivityTestRule = new ActivityTestRule<>(AccomodationMain.class);
     @Test
    public void clickable(){
         onView(withId(R.id.apply)).perform(click());
//         onView(withId(R.id.accomodationCard)).perform(click());
     }
}
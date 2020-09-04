package com.example.thewitsapp;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MenuActivityTest {

    @Rule
    public ActivityTestRule<MenuActivity> menuActivityActivityTestRule = new ActivityTestRule<>(MenuActivity.class);

//    @Test
//    public void clickProfileCard(){
//        onView(withId(R.id.profileCard)).perform(click());
//    I'll test after they have implemented
//    }
//    @Test
//    public void clickOverFlowCard(){
//        onView(withId(R.id.overflowCard)).perform(click());
//        //I'll be back for you
//    }
    @Test
    public void clickSafeSpaceCard(){
        onView(withId(R.id.safespaceCard)).perform(click());
        onView(withId(R.id.add_discussion)).perform(click());
    }
    @Test
    public void clickHealthCard(){
        onView(withId(R.id.healthCard)).perform(click());
        onView(withId(R.id.Student)).perform(click());
    }

    @Test
    public void clickAccommodationsCard(){
        onView(withId(R.id.accomodationCard)).perform(click());
        onView(withId(R.id.apply)).perform(click());
    }

    @Test
    public void clickCreativeCard(){
        onView(withId(R.id.creativesCard)).perform(click());
        onView(withId(R.id.photograpy)).perform(click());
    }
}
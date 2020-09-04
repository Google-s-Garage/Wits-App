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

    @Test
    public void clickProfileCard(){
        onView(withId(R.id.profileCard)).perform(click());
    }
    @Test
    public void clickOverFlowCard(){
        onView(withId(R.id.overflowCard)).perform(click());
    }
    @Test
    public void clickSafeSpaceCard(){
        onView(withId(R.id.safespaceCard)).perform(click());
    }
    @Test
    public void clickHealthCard(){
        onView(withId(R.id.healthCard)).perform(click());
    }
    @Test
    public void clickAccommodationsCard(){
        onView(withId(R.id.accomodationCard)).perform(click());
    }
    @Test
    public void clickCreativeCard(){
        onView(withId(R.id.creativesCard)).perform(click());
    }
}
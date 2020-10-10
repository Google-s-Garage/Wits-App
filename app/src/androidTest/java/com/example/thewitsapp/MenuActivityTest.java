package com.example.thewitsapp;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MenuActivityTest {

    @Rule
    public ActivityTestRule<MenuActivity> menuActivityActivityTestRule = new ActivityTestRule<>(MenuActivity.class);


    @Test
    public void clickOverflowCard(){
        onView(withText("Safe Space")).check(matches(isDisplayed()));
        onView(withId(R.id.overflowCard)).perform(click());
    }
    @Test
    public void clickSafeSpaceCard(){
        onView(withText("Safe Space")).check(matches(isDisplayed()));
        onView(withId(R.id.safespace)).perform(click());
    }
    @Test
    public void clickHealthCard(){
        onView(withText("Safe Space")).check(matches(isDisplayed()));
        onView(withId(R.id.health)).perform(click());
    }

}
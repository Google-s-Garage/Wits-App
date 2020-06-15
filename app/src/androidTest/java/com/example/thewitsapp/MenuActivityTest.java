package com.example.thewitsapp;

import androidx.test.espresso.Espresso;
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
public class MenuActivityTest {

    @Rule
    public ActivityTestRule<MenuActivity> menuActivityActivityTestRule = new ActivityTestRule<>(MenuActivity.class);
    public ActivityTestRule<overflow> overflowActivityTestRule = new ActivityTestRule<>(overflow.class);

    @Test
    public void clickableOverflow(){
        onView(withId(R.id.overflow)).perform(click());
        onView(withId(R.id.add_comment)).perform(click());
    }
    @Test
    public void clickableSafespace(){
                Espresso.onView(withId(R.id.safespace)).perform(click());
    }
    @Test
    public void clickabkeCreatives(){
                onView(withId(R.id.creatives)).perform(click());
    }
}
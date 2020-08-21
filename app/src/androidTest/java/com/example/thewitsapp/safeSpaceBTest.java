package com.example.thewitsapp;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class safeSpaceBTest {

    @Rule
    public ActivityTestRule<safeSpaceB> safeSpaceBActivityTestRule = new ActivityTestRule<>(safeSpaceB.class);

    @Test
    public void sendNote(){

        onView(withId(R.id.myDiscEditText)).perform(typeText("Hello"), closeSoftKeyboard());
//        onView(withText(R.id.MyDiscsendButton)).perform(click());

    }

}
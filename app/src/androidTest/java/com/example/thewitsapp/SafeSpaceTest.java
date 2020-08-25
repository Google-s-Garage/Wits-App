package com.example.thewitsapp;

import androidx.annotation.ContentView;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

//@RunWith(AndroidJUnit4.class)
//public class SafeSpaceTest {
//    @Rule
//    public ActivityTestRule<SafeSpace> safeSpaceActivityTestRule = new ActivityTestRule<>(SafeSpace.class);
//
//    @Test
//    public void ClickButtn(){
//        onView(withId(R.id.chatRoomBtn)).perform(click());
//    }
//
//}
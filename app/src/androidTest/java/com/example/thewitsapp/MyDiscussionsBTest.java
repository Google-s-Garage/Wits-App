//package com.example.thewitsapp;
//
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//import androidx.test.rule.ActivityTestRule;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.internal.matchers.And;
//
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.action.ViewActions.click;
//import static androidx.test.espresso.action.ViewActions.typeText;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//import static androidx.test.espresso.matcher.ViewMatchers.withText;
//import static org.junit.Assert.*;
//
//@RunWith(AndroidJUnit4.class)
//public class MyDiscussionsBTest {
//
//    @Rule
//    public ActivityTestRule<MyDiscussionsB> myDiscussionsBActivityTestRule = new ActivityTestRule<>(MyDiscussionsB.class);
//
//    @Test
//    public void wooow(){
//        onView(withId(R.id.myDiscEditText)).perform(typeText("Text"));
//        onView(withId(R.id.MyDiscsendButton)).perform(click());
//    }
//}
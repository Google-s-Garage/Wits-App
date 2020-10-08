//package com.example.thewitsapp;
//
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//import androidx.test.rule.ActivityTestRule;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.action.ViewActions.click;
//import static androidx.test.espresso.action.ViewActions.typeText;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static androidx.test.espresso.matcher.ViewMatchers.thatMatchesFirst;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//import static org.junit.Assert.*;
//
//@RunWith(AndroidJUnit4.class)
//public class beforeHealthTest {
//    @Rule
//    public ActivityTestRule<beforeHealth> beforeHealthActivityTestRule = new ActivityTestRule<>(beforeHealth.class);
//
//    @Test
//    public void enterHealth(){
//        onView(withId(R.id.restaurant)).perform(click());
//        //onView(withId(R.id.add_health_post)).perform(click());
//        onView(withId(R.id.picture)).check(matches(isDisplayed()));
//    }
//}
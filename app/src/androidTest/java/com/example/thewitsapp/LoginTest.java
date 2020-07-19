package com.example.thewitsapp;

import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.thatMatchesFirst;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginTest {

    @Rule
    public ActivityTestRule<Login> LoginTestRule = new ActivityTestRule<>(Login.class);


    @Test
    public void testLaunch(){
        onView(withText("Welcome To The Wits App")).check(matches(isDisplayed()));
//        onView(withId(R.id.Login)).perform(click());
//        onView(withId(R.id.Signup)).perform(click());
    }

    @Test
    public void loginTest(){
        onView(withId(R.id.Login)).perform(click());
        onView(withId(R.id.studentNumberLogin))
                .perform(typeText("1872817"), closeSoftKeyboard());

        Espresso.onView(withId(R.id.passwordLogin))
                .perform(typeText("khutso.1999"), closeSoftKeyboard());
        onView(withId(R.id.login_button))
                .perform(click());
    }

    @Test
    public void registerTest(){
        onView(withId(R.id.Signup)).perform(click());
        onView(withId(R.id.name))
                .perform(typeText("Me22"), closeSoftKeyboard());
        onView(withId(R.id.surname))
                .perform(typeText("Happy2"), closeSoftKeyboard());
        onView(withId(R.id.stdNumber))
                .perform(typeText("0072"), closeSoftKeyboard());
        onView(withId(R.id.createPass))
                .perform(typeText("01010"), closeSoftKeyboard());
        onView(withId(R.id.veriPass))
                .perform(typeText("01010"), closeSoftKeyboard());
        onView(withId(R.id.email))
                .perform(typeText("me2@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.veriEmail))
                .perform(typeText("me2@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.regiButton)).perform(click());

    }

}
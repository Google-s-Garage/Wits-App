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

@RunWith(AndroidJUnit4.class)
public class RegisterTest {

    @Rule
    public ActivityTestRule<Register> registerActivityTestRule = new ActivityTestRule<>(Register.class);

    @Test
    public void correctlogIn(){
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
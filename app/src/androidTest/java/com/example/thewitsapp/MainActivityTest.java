package com.example.thewitsapp;

import androidx.test.espresso.Espresso;
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
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void user_LogIn(){
        onView(withId(R.id.studentNumberLogin))
                .perform(typeText("1872817"), closeSoftKeyboard());

        Espresso.onView(withId(R.id.passwordLogin))
                .perform(typeText("khutso.1999"), closeSoftKeyboard());
        onView(withId(R.id.login_button))
                .perform(click());
    }
}
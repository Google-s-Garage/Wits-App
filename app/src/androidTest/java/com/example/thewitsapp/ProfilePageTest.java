package com.example.thewitsapp;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)

public class ProfilePageTest {

    @Rule
    public ActivityTestRule<ProfilePage> profilePageActivityTestRule = new ActivityTestRule<>(ProfilePage.class);

    @Test
    public void appears(){
        onView(withText("1810589")).check(matches(isDisplayed())); //I have to retreive the st no: from the variable
    }
}
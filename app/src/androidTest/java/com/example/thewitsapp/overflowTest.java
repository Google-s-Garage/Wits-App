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
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class overflowTest {

    @Rule
    public ActivityTestRule<overflow> overflowActivityTestRule = new ActivityTestRule<>(overflow.class);

    @Test
    public void clickFLoat(){
    onView(withId(R.id.add_comment)).perform(click());
    onView(withId(R.id.add_dialog_comment)).perform(typeText("Commented"), closeSoftKeyboard());
    onView(withId(R.id.add_comment_dialog_post_button)).perform(click());
    }
}
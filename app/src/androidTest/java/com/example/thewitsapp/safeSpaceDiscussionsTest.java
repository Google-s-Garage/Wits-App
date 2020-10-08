package com.example.thewitsapp;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class safeSpaceDiscussionsTest {

    @Rule
    public ActivityTestRule<safeSpaceDiscussions> safeSpaceDiscussionsActivityTestRule = new ActivityTestRule<>(safeSpaceDiscussions.class);

    @Test
    public void clickable(){
        onView(withId(R.id.viewPager)).check(matches(isDisplayed()));
    }
}
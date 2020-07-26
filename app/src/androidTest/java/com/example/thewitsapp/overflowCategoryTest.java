package com.example.thewitsapp;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class overflowCategoryTest {
    @Rule
    public ActivityTestRule<overflowCategory> overflowCategoryActivityTestRule = new ActivityTestRule<>(overflowCategory.class);

    @Test
    public void clickScience() {
        onView(withId(R.id.science_category)).perform(click());
    }
    @Test
    public void clickMath(){
        onView(withId(R.id.math_category)).perform(click());
    }
    // I could'nt test the Art, Accounting, Bio and Law because they are not visible in the screen
    // you have to scroll to view these

}
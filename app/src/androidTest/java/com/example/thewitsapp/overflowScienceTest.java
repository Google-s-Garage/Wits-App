package com.example.thewitsapp;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
@LargeTest
public class overflowScienceTest {

    @Rule
    public ActivityTestRule<overflowScience> overflowArtsActivityTestRule = new ActivityTestRule<>(overflowScience.class);

    @Test
    public void clickQuestionScience(){
        onView(withId(R.id.add_question_science)).perform(click());
    }
}
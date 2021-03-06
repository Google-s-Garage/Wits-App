package com.example.thewitsapp;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
@LargeTest
public class overflowArtsTest {

    @Rule
    public ActivityTestRule<overflowArts> overflowArtsActivityTestRule = new ActivityTestRule<>(overflowArts.class);

    @Test
    public void clickQuestionArt(){
        onView(withId(R.id.add_question_arts)).perform(click());
        onView(withId(R.id.add_dialog_comment)).perform(typeText("Commented"), closeSoftKeyboard());
        onView(withId(R.id.add_comment_dialog_post_button)).perform(click());
    }
}
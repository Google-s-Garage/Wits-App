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
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class inputQuestionTest {

    @Rule
    public ActivityTestRule<inputQuestion> inputQuestionActivityTestRule = new ActivityTestRule<>(inputQuestion.class);

    @Test
    public void enterQuestion(){
        onView(withId(R.id.question_box))
                .perform(typeText("Hello my name is Me2, where can I find the offices of Wits"), closeSoftKeyboard());
        onView((withId(R.id.post_button))).perform(click());
    }
}
package com.example.thewitsapp;

import android.view.View;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.And;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class overflowCommentTest {

    @Rule
    public ActivityTestRule<overflowComment> overflowActivityTestRule = new ActivityTestRule<overflowComment>(overflowComment.class);
    private overflowComment overflowComment = null;

    @Before
    public void setUp(){
        overflowComment = overflowActivityTestRule.getActivity();
    }
    @Test
    public void testEnterDetailClickBotton(){
        //onView(withId(R.id.commentBar))
              //  .perform(typeText("We love you"), closeSoftKeyboard());
       //onView(withId(R.id.sendCommentButton)).perform(click());
    }

    @After
    public void tearDown(){
        overflowComment = null;
    }
}
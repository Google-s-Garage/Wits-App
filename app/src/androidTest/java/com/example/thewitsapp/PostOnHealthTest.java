package com.example.thewitsapp;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class PostOnHealthTest {

    @Rule
    public ActivityTestRule<PostOnHealth> postOnHealthActivityTestRule = new ActivityTestRule<>(PostOnHealth.class);

    @Test
    public void postOnHealth(){
        onView(withId(R.id.health_post_name)).perform(typeText("Eat!!"));
//        onView(withId(R.id.health_descrip)).perform(typeText("Health"));
//        onView(withId(R.id.health_category)).perform(typeText("Tips"));
//        onView(withId(R.id.health_date)).perform(typeText("12/12/12"));
//        onView(withId(R.id.health_post_button)).perform(click());

    }
}
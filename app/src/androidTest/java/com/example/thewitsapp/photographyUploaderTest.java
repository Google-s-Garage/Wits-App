package com.example.thewitsapp;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class photographyUploaderTest {

    @Rule
    public ActivityTestRule<photographyUploader> photographyUploaderActivityTestRule = new ActivityTestRule<>(photographyUploader.class);

    @Test
    public void enterContacts() {
        onView(withId(R.id.enter_contacts)).perform(typeText("072 770 0256"));
    }
    @Test
    public void enterLocation() {
        onView(withId(R.id.enter_location)).perform(typeText("Wits art musuem"));
    }
//    @Test
//    public void enterDate() {
//        onView(withId(R.id.enter_date)).perform(typeText("01/01/2030"));
//    }
//    @Test
//    public void enterTime() {
//        onView(withId(R.id.enter_time)).perform(typeText("12:30"));
//    }
//    @Test
//    public void enterDescription(){
//        onView(withId(R.id.enter_description)).perform(typeText("Explore local artistic ideas"));
//    }
}
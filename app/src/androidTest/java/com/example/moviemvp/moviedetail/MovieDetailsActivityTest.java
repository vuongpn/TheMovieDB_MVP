package com.example.moviemvp.moviedetail;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.moviemvp.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MovieDetailsActivityTest {
    @Rule
    public ActivityTestRule movieDetailActivity = new ActivityTestRule<>(MovieDetailsActivity.class);

    @Test
    public void testButton() {
        onView(withId(R.id.edtRate)).perform(typeText("9"));
        onView(withId(R.id.btn)).perform(click());
    }
}
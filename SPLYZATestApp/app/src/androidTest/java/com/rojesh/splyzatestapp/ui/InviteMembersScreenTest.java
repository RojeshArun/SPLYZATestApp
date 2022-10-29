package com.rojesh.splyzatestapp.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.rojesh.splyzatestapp.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class InviteMembersScreenTest {

    @Rule
    public ActivityScenarioRule<HomeScreen> mActivityRule
            = new ActivityScenarioRule<>(HomeScreen.class);

    @Test
    public void testBackButtonClick() {
        onView(withId(R.id.btn_invite)).perform(click());
        onView(withId(R.id.btn_back)).perform(click());
        onView(withId(R.id.home_screen_lyt)).check(matches(isDisplayed()));
    }


}
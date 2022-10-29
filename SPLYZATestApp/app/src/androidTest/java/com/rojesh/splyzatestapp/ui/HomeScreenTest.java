package com.rojesh.splyzatestapp.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.rojesh.splyzatestapp.R;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class HomeScreenTest {

    @Rule
    public ActivityScenarioRule<HomeScreen> mActivityRule
            = new ActivityScenarioRule<>(HomeScreen.class);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testUserInviteButtonClick() {
        Espresso.onView(withId(R.id.btn_invite)).perform(click());
        onView(withId(R.id.invite_members_lyt)).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {
    }
}
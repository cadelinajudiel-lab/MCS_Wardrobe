package com.example.myapplication;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class PlaceOrderTest {

    @Rule
    public ActivityScenarioRule<PlaceOrder> activityRule =
            new ActivityScenarioRule<>(PlaceOrder.class);

    @Test
    public void testPlaceOrderButtonIsInteractable() {
        // Verify the button is displayed
        onView(withId(R.id.btn_place_order)).check(matches(isDisplayed()));

        // Test clicking the button
        onView(withId(R.id.btn_place_order)).perform(click());
    }
}

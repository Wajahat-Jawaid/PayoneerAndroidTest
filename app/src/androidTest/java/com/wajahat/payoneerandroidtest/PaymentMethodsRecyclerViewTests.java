package com.wajahat.payoneerandroidtest;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.wajahat.payoneerandroidtest.RecyclerViewMatcher.nthChildOf;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
@RunWith(AndroidJUnit4.class)
public class PaymentMethodsRecyclerViewTests extends AbstractTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testPaymentMethodsRecyclerView_clicks() {
        onView(withId(R.id.recyclerview_payment_methods))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    @Test
    public void testPaymentMethodsRecyclerView_scrolls() {
        onView(ViewMatchers.withId(R.id.recyclerview_payment_methods))
                .perform(RecyclerViewActions.scrollToPosition(8));
    }

    @Test
    public void testPaymentMethodsRecyclerView_display() {
        onView(allOf(withId(R.id.text_card_label), isDescendantOfA(nthChildOf(withId(R.id.recyclerview_payment_methods), 0))))
                .check(matches(isDisplayed()));
    }
}
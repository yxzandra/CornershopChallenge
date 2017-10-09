package com.yxzandra.cornershopchallenge;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yxzandra.cornershopchallenge.mvp.views.MainActivity;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by yxzan on 08/10/2017.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityEspressoTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void ensureRemoveElement() {
        RecyclerView recyclerView = (RecyclerView) mActivityRule.getActivity().findViewById(R.id.rvCounter);
        int itemCount = recyclerView.getAdapter().getItemCount();

        if (itemCount != 0) {
            onView(withId(R.id.rvCounter)).perform(
                    RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.ivDelete)));
            onView(withId(R.id.rvCounter)).check(new RecyclerViewItemCountAssertion(itemCount - 1));
        }
    }

    @Test
    public void ensureTextChangesWorkByIncreasing() {
        int total = Integer.parseInt(getText(withId(R.id.tvTotal)));

        onView(withId(R.id.rvCounter)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.ivAdd)));

        onView(withId(R.id.tvTotal))
                .check(matches(withText(String.valueOf(total+1))));
    }

    @Test
    public void ensureTextChangesWorkByDecrementing() {
        int total = Integer.parseInt(getText(withId(R.id.tvTotal)));

        onView(withId(R.id.rvCounter)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.ivMinus)));

        onView(withId(R.id.tvTotal))
                .check(matches(withText(String.valueOf(total-1))));
    }

    String getText(final Matcher<View> matcher) {
        final String[] stringHolder = { null };
        onView(matcher).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(TextView.class);
            }

            @Override
            public String getDescription() {
                return "getting text from a TextView";
            }

            @Override
            public void perform(UiController uiController, View view) {
                TextView tv = (TextView)view; //Save, because of check in getConstraints()
                stringHolder[0] = tv.getText().toString();
            }
        });
        return stringHolder[0];
    }


    public static ViewAction clickChildViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                v.performClick();
            }
        };
    }

    public class RecyclerViewItemCountAssertion implements ViewAssertion {
        private final int expectedCount;

        public RecyclerViewItemCountAssertion(int expectedCount) {
            this.expectedCount = expectedCount;
        }

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }

            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            assertThat(adapter.getItemCount(), is(expectedCount));
        }
    }



}

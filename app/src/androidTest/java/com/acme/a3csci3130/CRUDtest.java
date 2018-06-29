package com.acme.a3csci3130;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.espresso.action.ViewActions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class CRUDtest {

    @Rule
    public ActivityTestRule<MainActivity> Create =
            new ActivityTestRule(MainActivity.class);


    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.acme.a3csci3130", appContext.getPackageName());
    }

    @Test
    public void CreateContactAndReadContact() {
        //create a contact first
        onView(withId(R.id.CreateButton)).perform(click());
        onView(withId(R.id.name)).perform(typeText("Yahu"));
        onView(withId(R.id.email)).perform(typeText("yh288977@dal.ca"));
        onView(withId(R.id.businessnumber)).perform(typeText("123456789"));
        onView(withId(R.id.primarybusiness)).perform(typeText("Fisher"));
        onView(withId(R.id.address)).perform(typeText("Whatever"));
        onView(withId(R.id.province)).perform(typeText("AB")).perform(closeSoftKeyboard());
        onView(withId(R.id.submitButton)).perform(click());

        //click first contact
        onData(org.hamcrest.Matchers.allOf()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());

        //check if create successful
        onView(withId(R.id.name)).check(matches(withText("Yahu")));
        onView(withId(R.id.email)).check(matches(withText("yh288977@dal.ca")));
        onView(withId(R.id.businessnumber)).check(matches(withText("123456789")));
        onView(withId(R.id.primarybusiness)).check(matches(withText("Fisher")));
        onView(withId(R.id.address)).check(matches(withText("Whatever")));
        onView(withId(R.id.province)).check(matches(withText("AB")));

        onView(withId(R.id.deleteButton)).perform(click());
    }

    @Test
    public void DelectContact() {
        onView(withId(R.id.CreateButton)).perform(click());
        onView(withId(R.id.name)).perform(typeText("Yahu"));
        onView(withId(R.id.email)).perform(typeText("yh288977@dal.ca"));
        onView(withId(R.id.businessnumber)).perform(typeText("123456789"));
        onView(withId(R.id.primarybusiness)).perform(typeText("Fisher"));
        onView(withId(R.id.address)).perform(typeText("Whatever"));
        onView(withId(R.id.province)).perform(typeText("AB")).perform(closeSoftKeyboard());
        onView(withId(R.id.submitButton)).perform(click());


        onData(org.hamcrest.Matchers.allOf()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());


        onView(withId(R.id.name)).check(matches(withText("Yahu")));
        onView(withId(R.id.email)).check(matches(withText("yh288977@dal.ca")));
        onView(withId(R.id.businessnumber)).check(matches(withText("123456789")));
        onView(withId(R.id.primarybusiness)).check(matches(withText("Fisher")));
        onView(withId(R.id.address)).check(matches(withText("Whatever")));
        onView(withId(R.id.province)).check(matches(withText("AB")));

        onView(withId(R.id.deleteButton)).perform(click());
        onData(org.hamcrest.Matchers.allOf()).inAdapterView(withId(R.id.listView)).atPosition(0).equals(null);
    }

    @Test
    public void UpdateContact() {
        onView(withId(R.id.CreateButton)).perform(click());
        onView(withId(R.id.name)).perform(typeText("YahuWang"));
        onView(withId(R.id.email)).perform(typeText("yh288977@dal.ca"));
        onView(withId(R.id.businessnumber)).perform(typeText("123456789"));
        onView(withId(R.id.primarybusiness)).perform(typeText("Fisher"));
        onView(withId(R.id.address)).perform(typeText("Whatever"));
        onView(withId(R.id.province)).perform(typeText("AB")).perform(closeSoftKeyboard());
        onView(withId(R.id.submitButton)).perform(click());


        onData(org.hamcrest.Matchers.allOf()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());

        onView(withId(R.id.name)).check(matches(withText("YahuWang")));
        onView(withId(R.id.email)).check(matches(withText("yh288977@dal.ca")));
        onView(withId(R.id.businessnumber)).check(matches(withText("123456789")));
        onView(withId(R.id.primarybusiness)).check(matches(withText("Fisher")));
        onView(withId(R.id.address)).check(matches(withText("Whatever")));
        onView(withId(R.id.province)).check(matches(withText("AB")));

        onView(withId(R.id.name)).perform(replaceText("Wang"));
        onView(withId(R.id.email)).perform(replaceText("997639410@qq.com"));
        onView(withId(R.id.businessnumber)).perform(replaceText("987654321"));
        onView(withId(R.id.primarybusiness)).perform(replaceText("Distributor"));
        onView(withId(R.id.address)).perform(replaceText("Earth"));
        onView(withId(R.id.province)).perform(replaceText("BC")).perform(closeSoftKeyboard());
        onView(withId(R.id.updateButton)).perform(click());

        onData(org.hamcrest.Matchers.allOf()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());

        onView(withId(R.id.name)).check(matches(withText("Wang")));
        onView(withId(R.id.email)).check(matches(withText("997639410@qq.com")));
        onView(withId(R.id.businessnumber)).check(matches(withText("987654321")));
        onView(withId(R.id.primarybusiness)).check(matches(withText("Distributor")));
        onView(withId(R.id.address)).check(matches(withText("Earth")));
        onView(withId(R.id.province)).check(matches(withText("BC")));

        onView(withId(R.id.deleteButton)).perform(click());

    }
}

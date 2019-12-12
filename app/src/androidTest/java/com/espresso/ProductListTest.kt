package com.espresso

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.rule.ActivityTestRule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.espresso.activity.ProductListActivity
import org.hamcrest.Matchers


@RunWith(AndroidJUnit4ClassRunner::class)
class ProductListTest {


    @get:Rule
    var activityRule: ActivityTestRule<ProductListActivity> = ActivityTestRule(
        ProductListActivity::class.java
    )


    @Test
    fun clickOnRecyclerView() {

        onView(withId(R.id.recycler_view)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click())
        )

    }


    @Test
    fun clickOnSpinnerItem() {

        onView(withId(R.id.recycler_view)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click())
        )
        onView(withId(R.id.product_quantity)).perform(click())
        onData(Matchers.anything()).atPosition(2).perform(click())
        onView(withId(R.id.add_to_cart_btn)).perform(click())
    }

    @Test
    fun clickOnCartIcon() {

        onView(withId(R.id.recycler_view)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click())
        )

        onView(withId(R.id.product_quantity)).perform(click())
        onData(Matchers.anything()).atPosition(4).perform(click())
        onView(withId(R.id.add_to_cart_btn)).perform(click())
        pressBack()

        onView(withId(R.id.recycler_view)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(7, click())
        )

        onView(withId(R.id.product_quantity)).perform(click())
        onData(Matchers.anything()).atPosition(2).perform(click())
        onView(withId(R.id.add_to_cart_btn)).perform(click())

        onView(withId(R.id.cart_icon_view)).perform(click())
        onView(withId(R.id.check_out)).perform(click())

    }


    @Test
    fun recyclerViewScroll() {

        val recyclerView: RecyclerView = activityRule.activity.findViewById(R.id.recycler_view)
        var itemCount: Int = recyclerView.adapter!!.itemCount

        onView(withId(R.id.recycler_view)).inRoot(
            RootMatchers.withDecorView(
                Matchers.`is`
                    (activityRule.activity.window.decorView)
            )
        ).perform(scrollToPosition<RecyclerView.ViewHolder>(itemCount - 1))


    }


}




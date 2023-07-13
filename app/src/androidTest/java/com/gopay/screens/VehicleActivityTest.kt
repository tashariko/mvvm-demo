package com.gopay.screens

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.gopay.screens.ui.VehicleActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.gopay.R
import com.gopay.screens.data.VehicleViewModel
import com.gopay.screens.ui.VehicleAdapter
import com.gopay.util.EspressoIdlingResourceRule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class VehicleActivityTest {
    @get: Rule
    var espressoIdlingResourceRule = EspressoIdlingResourceRule()

    @get: Rule
    var activityScenarioRule = activityScenarioRule<VehicleActivity>()

    lateinit var activityScenario: ActivityScenario<VehicleActivity>

    @Before
    fun setup() {
        activityScenario = ActivityScenario.launch(VehicleActivity::class.java)

        activityScenario.moveToState(Lifecycle.State.RESUMED)
    }

    @After
    fun stop() {
        activityScenario.moveToState(Lifecycle.State.DESTROYED)
    }

    @Test
    fun test_execute() {
        onView(withId(R.id.recyclerView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<VehicleAdapter.VehicleViewHolder>(3,click()))
        onView(withId(R.id.textView)).check(ViewAssertions.matches(withText("Death Star")))
    }
}

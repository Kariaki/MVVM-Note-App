package com.votenoid.myapplication

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class FirstActivityTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<FirstActivity>
            = ActivityScenarioRule(FirstActivity::class.java)
    lateinit var activity: FirstActivity


    @Before
    fun setUp() {
    }

    @Test
    fun testLaunch(){
        onView(withId(R.id.writter)).perform(typeText("bright is funny"))
        Espresso.closeSoftKeyboard()

    }
    @Test
    fun testLoginInput(){
    }
    @After
    fun tearDown() {


    }
}
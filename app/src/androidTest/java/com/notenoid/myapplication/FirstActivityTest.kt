package com.notenoid.myapplication

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest

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

    }
    @Test
    fun testLoginInput(){
    }
    @After
    fun tearDown() {


    }
}
package com.example.myplaylistapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlayListFeature {

    val mActivityRule = ActivityTestRule(MainActivity::class.java)
        @Rule get

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.myplaylistapp", appContext.packageName)
    }

    @Test
    fun displayScreenTitle() {
        assertDisplayed("MyPlaylistApp")
    }
}
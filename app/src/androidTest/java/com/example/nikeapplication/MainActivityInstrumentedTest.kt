package com.example.nikeapplication

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.nikeapplication.CustomAssertions.Companion.hasItemCount
import com.example.nikeapplication.CustomMatchers.Companion.typeSearchViewText
import com.example.nikeapplication.view.MainActivity
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @Rule
    @JvmField
    val rule : ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.nikeapplication", appContext.packageName)
    }

    @Test
    fun userCanSearch() {
        // launch activity
        ActivityScenario.launch(MainActivity::class.java)

        // check if view is visible
        onView(withId(R.id.word_search)).check(ViewAssertions.matches(isDisplayed()))

        // enter text
        onView(withId(R.id.word_search)).perform(typeSearchViewText("words"))

        // check there are 10 items
        onView(withId(R.id.recycler_view)).check(hasItemCount(10))
    }
}
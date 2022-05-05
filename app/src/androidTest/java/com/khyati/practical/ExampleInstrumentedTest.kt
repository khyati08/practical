package com.khyati.practical

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.khyati.practical.viewmodel.FirstViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java, true, true)

    private lateinit var viewModel: FirstViewModel
/*
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.khyati.practical", appContext.packageName)
    }*/

    @Before
    fun setUp() {
        // Mock our View Model to stub out calls later
        viewModel = mock()

    }
}
package br.com.ibarra.clima

import android.support.test.espresso.intent.Intents
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HomeActivityTest {
    @Rule
    @JvmField
    val rule = ActivityTestRule<HomeActivity>(HomeActivity::class.java, true, true)

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun shouldDisplayWeatherText() {
        homeActivity {
            weatherFieldsAreVisible()
        }
    }

    @Test
    fun shouldDisplayCurrentCity() {
        homeActivity {
            currentCityIs("Helsinki")
        }
    }

    @Test
    fun whenClickSettingsButton_shouldRedirectToNextActivity() {
        homeActivity {

        } clickFormatButton {
            redirectToNextActivity()
        }
    }
}
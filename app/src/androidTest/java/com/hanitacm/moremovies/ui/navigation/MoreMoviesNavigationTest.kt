package com.hanitacm.moremovies.ui.navigation

import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hanitacm.moremovies.MainActivity
import com.hanitacm.moremovies.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith



@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MoreMoviesNavigationTest {
    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        hiltTestRule.inject()
    }

    @Test
    fun verifyMainScreenIsStartDestination() {
        startActivity()

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.app_name))
            .assertIsDisplayed()
    }

    @Test
    fun clickOnAMovie_navigate_DetailScreen() {
        startActivity()

        composeTestRule.onAllNodes(hasClickAction()).onFirst().performClick()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, Screens.MovieDetail.route)
    }

    private fun startActivity() {
        composeTestRule.activity.setContent {
            // Creates a TestNavHostController
            navController =
                TestNavHostController(LocalContext.current)
            // Sets a ComposeNavigator to the navController so it can navigate through composables
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            MoreMoviesNavigation(navController = navController)
        }
    }
}

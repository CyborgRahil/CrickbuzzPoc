package poc.com.cricbuzzpoc.com.crickbuzzpoc.addDrug

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers.`is`


import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import poc.com.cricbuzzpoc.addDrug.view.AddDrugActivity
import poc.com.cricbuzzpoc.addDrug.view.AddDrugPresenter
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import kotlinx.android.synthetic.main.activity_add_med.*
import org.hamcrest.Matchers.*
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import poc.com.cricbuzzpoc.R
import poc.com.cricbuzzpoc.addDrug.view.AddDrugContract
import poc.com.cricbuzzpoc.data.local.DrugEntity
import poc.com.cricbuzzpoc.stub.AddDrugPresenterMock
import kotlin.test.assertEquals


@RunWith(AndroidJUnit4::class)
class AddDrugActivityTest {

    @Rule
    @JvmField
    val activityRule =
            ActivityTestRule<AddDrugActivity>(AddDrugActivity::class.java, true, false)


    val  mAddPresenter = AddDrugPresenterMock()


    @Test
    fun checkAddMedFrequencySpinnerTest() {
        activityRule.launchActivity(null)
        onView(withId(R.id.add_med_frequency)).perform(click())
        // Click on the first item from the list, which is a marker string: "Select your country"
        onData(allOf(`is`(instanceOf(String::class.java)))).atPosition(0).perform(click())

        onView(withId(R.id.add_med_frequency)).check(matches(not(withText("Every Day"))))

    }

    @Test
    fun checkDateBoxVisibilityTest() {
        activityRule.launchActivity(null)
        onView(withId(R.id.add_med_frequency)).perform(click())
        // Click on the first item from the list, which is a marker string: "Select your country"
        onData(allOf(`is`(instanceOf(String::class.java)))).atPosition(1).perform(click())

        onView(withId(R.id.med_date_picker)).check(matches(isDisplayed()))

    }

    @Test
    @Throws(Exception::class)
    fun testLeaveView() {
        activityRule.launchActivity(null)
        activityRule.activity.onDetachedFromWindow()
        assertEquals(false,mAddPresenter.viewAttached)
    }

    @Test
    @Throws(Exception::class)
    fun testReturnToView() {
        activityRule.launchActivity(null)
        mAddPresenter.takeView(activityRule.activity)
        assertEquals(true,mAddPresenter.viewAttached)
    }

    @Test
    fun addDrugUiTest() {
        activityRule.launchActivity(null)
        onView(withId(R.id.add_med_name)).check(matches(isDisplayed()))
        onView(withId(R.id.add_med_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.add_dosage_quantity)).check(matches(isDisplayed()))
        onView(withId(R.id.med_time_picker)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.med_date_picker)).check(matches(not(isDisplayed())))
    }

}
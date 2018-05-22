package poc.com.cricbuzzpoc.com.crickbuzzpoc.drugDescription

import android.content.Intent
import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.view.View
import org.junit.Rule
import org.junit.Test
import poc.com.cricbuzzpoc.R
import poc.com.cricbuzzpoc.com.crickbuzzpoc.stub.DrugDescriptionPresenterMock
import poc.com.cricbuzzpoc.drugDescription.view.DrugDescriptionActivity

import kotlin.test.assertEquals
import android.widget.EditText
import android.widget.TextView
import org.hamcrest.*
import poc.com.cricbuzzpoc.TestUtil
import poc.com.cricbuzzpoc.utility.MedicalAppConstants


class DrugDescriptionActivityTest {

    @Rule
    @JvmField
    val activityRule =
            ActivityTestRule<DrugDescriptionActivity>(DrugDescriptionActivity::class.java, true, false)


    val mDescriptionPresenter = DrugDescriptionPresenterMock()


    @Test
    @Throws(Exception::class)
    fun testLeaveView() {
        startIntent()
        activityRule.activity.onDetachedFromWindow()
        assertEquals(false, mDescriptionPresenter.viewAttached)
    }

    @Test
    @Throws(Exception::class)
    fun testReturnToView() {
        startIntent()
        mDescriptionPresenter.takeView(activityRule.activity)
        assertEquals(true, mDescriptionPresenter.viewAttached)
    }

    @Test
    fun drugDescriptionUiTest() {
        startIntent()
        Espresso.onView(ViewMatchers.withId(R.id.drug_description)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.drug_name)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.btn_med_rejected)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.btn_med_taken)).check(ViewAssertions.matches((ViewMatchers.isDisplayed())))
    }

    fun isEditTextValueEqualTo(content: String): Matcher<View> {

        return object : TypeSafeMatcher<View>() {

            override fun describeTo(description: Description) {
                description.appendText("Match Edit Text Value with View ID Value : :  $content")
            }

            override fun matchesSafely(view: View?): Boolean {
                if (view !is TextView && view !is EditText) {
                    return false
                }
                if (view != null) {
                    val text: String
                    if (view is TextView) {
                        text = (view as TextView).text.toString()
                    } else {
                        text = (view as EditText).text.toString()
                    }

                    return text.equals(content, ignoreCase = true)
                }
                return false
            }
        }
    }

    fun startIntent(){
        var intent = Intent()
        intent.putExtra(MedicalAppConstants.DRUG_ID,"123")
        activityRule.launchActivity(intent)
    }

}
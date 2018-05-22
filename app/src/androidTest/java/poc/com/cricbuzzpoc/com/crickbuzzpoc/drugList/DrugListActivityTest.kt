package poc.com.cricbuzzpoc.com.crickbuzzpoc.drugList

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import poc.com.cricbuzzpoc.CustomAssertion
import poc.com.cricbuzzpoc.R
import poc.com.cricbuzzpoc.addDrug.view.AddDrugActivity
import poc.com.cricbuzzpoc.com.crickbuzzpoc.stub.DrugListPresenterMock
import poc.com.cricbuzzpoc.drugList.view.DrugListActivity
import poc.com.cricbuzzpoc.stub.AddDrugPresenterMock
import kotlin.test.assertEquals
class DrugListActivityTest {

    @Rule
    @JvmField
    val activityRule =
            ActivityTestRule<DrugListActivity>(DrugListActivity::class.java, true, false)


    val  mDrugListPresenter = DrugListPresenterMock()

    @Test
    fun `populate_drug_list`() {
        activityRule.launchActivity(null)
        mDrugListPresenter.takeView(activityRule.activity)
        mDrugListPresenter.getDrugList()
        onView(withId(R.id.drugs_recycler_view))
                .check(CustomAssertion.hasItemCount(1))

    }


    @Test
    @Throws(Exception::class)
    fun testLeaveView() {
        activityRule.launchActivity(null)
        activityRule.activity.onDetachedFromWindow()
        assertEquals(false,mDrugListPresenter.viewAttached)
    }

    @Test
    @Throws(Exception::class)
    fun testReturnToView() {
        activityRule.launchActivity(null)
        mDrugListPresenter.takeView(activityRule.activity)
        assertEquals(true,mDrugListPresenter.viewAttached)
    }
}
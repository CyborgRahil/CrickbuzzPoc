package poc.com.cricbuzzpoc.com.crickbuzzpoc.drugReport

import android.support.test.espresso.Espresso
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import kotlinx.android.synthetic.main.activity_report.*
import org.junit.Rule
import org.junit.Test
import poc.com.cricbuzzpoc.CustomAssertion
import poc.com.cricbuzzpoc.R
import poc.com.cricbuzzpoc.com.crickbuzzpoc.stub.ReportPresenterMock
import poc.com.cricbuzzpoc.report.view.ReportActivity
import poc.com.cricbuzzpoc.report.view.ReportPresenter
import kotlin.test.assertEquals

class DrugReportActivityTest {

    @Rule
    @JvmField
    val activityRule =
            ActivityTestRule<ReportActivity>(ReportActivity::class.java, true, false)


    val  mReportPresenter = ReportPresenterMock()

    @Test
    fun `populate_drug_list`() {
        activityRule.launchActivity(null)
        mReportPresenter.takeView(activityRule.activity)
        mReportPresenter.getDrugList()
        Espresso.onView(ViewMatchers.withId(R.id.rv_report))
                .check(CustomAssertion.hasItemCount(1))

    }


    @Test
    @Throws(Exception::class)
    fun testLeaveView() {
        activityRule.launchActivity(null)
        activityRule.activity.onDetachedFromWindow()
        assertEquals(false,mReportPresenter.viewAttached)
    }

    @Test
    @Throws(Exception::class)
    fun testReturnToView() {
        activityRule.launchActivity(null)
        mReportPresenter.takeView(activityRule.activity)
        assertEquals(true,mReportPresenter.viewAttached)
    }
}
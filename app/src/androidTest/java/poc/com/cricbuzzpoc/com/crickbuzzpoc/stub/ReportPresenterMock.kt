package poc.com.cricbuzzpoc.com.crickbuzzpoc.stub

import poc.com.cricbuzzpoc.TestUtil
import poc.com.cricbuzzpoc.drugList.view.DrugListContract
import poc.com.cricbuzzpoc.report.view.ReportContract

class ReportPresenterMock: ReportContract.Presenter  {
    var viewAttached : Boolean =false
    var mView : ReportContract.ReportView ?=null
    override fun getDrugList() {


        mView!!.populateRecyclerView(listOf(TestUtil.createEntity()))
    }

    override fun takeView(view: ReportContract.ReportView) {
       viewAttached = true
    }

    override fun dropView() {
       viewAttached = false
    }
}
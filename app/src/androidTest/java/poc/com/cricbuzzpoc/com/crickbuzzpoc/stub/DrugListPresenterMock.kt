package poc.com.cricbuzzpoc.com.crickbuzzpoc.stub

import poc.com.cricbuzzpoc.TestUtil
import poc.com.cricbuzzpoc.data.local.DrugEntity
import poc.com.cricbuzzpoc.drugList.view.DrugListContract

class DrugListPresenterMock : DrugListContract.Presenter {

    var viewAttached : Boolean =false
    var mView : DrugListContract.DrugListView ?=null

    override fun getDrugList() {
        mView!!.populateRecyclerView(listOf(TestUtil.createEntity()))
    }

    override fun openDrugListItem(drugEntity: DrugEntity) {

    }

    override fun drugReportGen(isMedicineIntake: Boolean) {

    }

    override fun takeView(view: DrugListContract.DrugListView) {
       viewAttached = true
        mView =view
    }

    override fun dropView() {
       viewAttached = false
    }
}
package poc.com.cricbuzzpoc.com.crickbuzzpoc.stub

import poc.com.cricbuzzpoc.TestUtil
import poc.com.cricbuzzpoc.data.local.DrugEntity
import poc.com.cricbuzzpoc.drugDescription.view.DrugDescriptionContract

class DrugDescriptionPresenterMock : DrugDescriptionContract.Presenter {
    var viewAttached : Boolean =false
    var mView:DrugDescriptionContract.DrugDescriptionView ?= null
    override fun getUserMedicineData(drugId: String) {
        mView!!.updateView(TestUtil.createEntity())
    }

    override fun updateDrugData(drugEntity: DrugEntity) {

    }

    override fun takeView(view: DrugDescriptionContract.DrugDescriptionView) {
       viewAttached = true
        mView = view
    }

    override fun dropView() {
       viewAttached = false
    }
}
package poc.com.cricbuzzpoc.stub


import poc.com.cricbuzzpoc.TestUtil
import poc.com.cricbuzzpoc.addDrug.view.AddDrugContract
import poc.com.cricbuzzpoc.data.local.DrugEntity

class AddDrugPresenterMock : AddDrugContract.Presenter {
    var viewAttached : Boolean =false
    override fun addDrugData(drugEntity: DrugEntity) {
        TestUtil.createEntity()
        return
    }

    override fun takeView(view: AddDrugContract.AddDrugView) {
        viewAttached = true
    }

    override fun dropView() {
        viewAttached = false
    }
}
package poc.com.cricbuzzpoc.drugList.view

import poc.com.cricbuzzpoc.base.BasePresenter
import poc.com.cricbuzzpoc.base.BaseView
import poc.com.cricbuzzpoc.data.local.DrugEntity

interface DrugListContract {

    interface DrugListView : BaseView<Presenter> {
        fun populateRecyclerView(list:List<DrugEntity>)
        fun fabItemClick()
        fun showDrugDetailsUi(drugId:String)
        fun drugReportUi(isMedicineIntake:Boolean)
    }

    interface Presenter : BasePresenter<DrugListView> {

        fun getDrugList()
        fun openDrugListItem(drugEntity: DrugEntity)
        fun drugReportGen(isMedicineIntake:Boolean)

    }

}
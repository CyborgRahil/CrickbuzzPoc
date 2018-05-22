package poc.com.cricbuzzpoc.drugDescription.view

import poc.com.cricbuzzpoc.base.BasePresenter
import poc.com.cricbuzzpoc.base.BaseView
import poc.com.cricbuzzpoc.data.local.DrugEntity


interface DrugDescriptionContract {

    interface DrugDescriptionView: BaseView<Presenter> {
       fun updateView(drugData:DrugEntity)
       fun updateDrugData(drugData: DrugEntity, isMediCineTaken:Boolean)
        fun showSuccessMessage(message:String)

    }

    interface Presenter : BasePresenter<DrugDescriptionView> {

        fun getUserMedicineData(drugId:String)
        fun updateDrugData(drugEntity: DrugEntity)
    }
}
package poc.com.cricbuzzpoc.addDrug.view

import poc.com.cricbuzzpoc.base.BasePresenter
import poc.com.cricbuzzpoc.base.BaseView
import poc.com.cricbuzzpoc.data.local.DrugEntity

interface AddDrugContract {

    interface AddDrugView: BaseView<Presenter> {
        fun showSuccessMessage(message:String)
        fun saveDrugData(drugEntity: DrugEntity)
        fun showDatePicker()
        fun showTimePicker()
        fun getDateFromPicker()
        fun setMedAlarm()
        fun initialiseDatePickerDialog()
        fun initialiseTimePickerDialog()

    }

    interface Presenter : BasePresenter<AddDrugView> {

        fun addDrugData(drugEntity: DrugEntity)

    }
}
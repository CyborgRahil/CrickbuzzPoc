package poc.com.cricbuzzpoc.drugDescription.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_drug_description.*
import kotlinx.android.synthetic.main.activity_drug_description.view.*
import poc.com.cricbuzzpoc.R
import poc.com.cricbuzzpoc.base.BaseActivity
import poc.com.cricbuzzpoc.data.local.DrugEntity
import poc.com.cricbuzzpoc.data.local.DrugReportEntity
import poc.com.cricbuzzpoc.utility.MedicalAppConstants
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class DrugDescriptionActivity : BaseActivity(), DrugDescriptionContract.DrugDescriptionView {

    @Inject
    lateinit var mDrugDescriptionPresenter: DrugDescriptionPresenter

    lateinit var mDrugEntity: DrugEntity

    /***
     * Show success message if drug entity is successfully updated into DB
     */
    override fun showSuccessMessage(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun updateDrugData(drugData: DrugEntity, isMediCineTaken: Boolean) {
        when (isMediCineTaken) {
            true -> mDrugEntity.drugTaken = mDrugEntity.drugTaken + 1
            false -> mDrugEntity.drugIgnore = mDrugEntity.drugIgnore + 1
        }
        if (mDrugEntity.drugTakenOrIgnoreDateList == null) {
            mDrugEntity.drugTakenOrIgnoreDateList = ArrayList()
        } else {
            val myFormat = "MM/dd/yyyy HH:mm" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            var date = Date()
            (mDrugEntity.drugTakenOrIgnoreDateList as ArrayList<DrugReportEntity>).add(DrugReportEntity(isMediCineTaken, sdf.format(date).toString()))

        }
        mDrugDescriptionPresenter.updateDrugData(mDrugEntity)
    }

    override fun updateView(drugData: DrugEntity) {
        with(drugData) {
            mDrugEntity = drugData
            tv_drug_name.text = drugData.drugName
            tv_drug_description.text = drugData.drugDescription
            tv_drug_dose.text = drugData.drugDoseQuantity +" " +drugData.drugType
            tv_drug_timing.text = drugData.drugFrequency + " "+drugData.drugAlarmTime

        }
    }
    /**
     * Show error if found any issue while getting and updating data in DB
     */
    override fun showError(errorMessage: String) {
        Toast.makeText(this,errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getBaliActivityComponent().inject(this)
        setContentView(R.layout.activity_drug_description)
        var drugId = intent.getStringExtra(MedicalAppConstants.DRUG_ID)
        mDrugDescriptionPresenter.getUserMedicineData(drugId)
        btn_med_taken.setOnClickListener({ updateDrugData(mDrugEntity, true) })
        btn_med_rejected.setOnClickListener({ updateDrugData(mDrugEntity, false) })

    }

    override fun onStop() {
        super.onStop()
        mDrugDescriptionPresenter.dropView()
    }

    override fun onStart() {
        super.onStart()
        mDrugDescriptionPresenter.takeView(this)

    }
}
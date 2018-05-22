package poc.com.cricbuzzpoc.addDrug.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.DatePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_med.*
import poc.com.cricbuzzpoc.R
import poc.com.cricbuzzpoc.base.BaseActivity
import poc.com.cricbuzzpoc.data.local.DrugEntity
import poc.com.cricbuzzpoc.helper.DrugListAlarmManager
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class AddDrugActivity : BaseActivity(),AddDrugContract.AddDrugView {


    @Inject
    lateinit var mAddDrugPresenter: AddDrugPresenter

    lateinit var mDrugEntity: DrugEntity

    var cal = Calendar.getInstance()

    var medDate: String ? =null

    var medTime: String ? =null

    lateinit var datePickerDialog: DatePickerDialog

    lateinit var timePickerDialog: TimePickerDialog

    override fun initialiseDatePickerDialog() {
        var listener =DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            getDateFromPicker()
        }
        datePickerDialog = DatePickerDialog(this,listener,  cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
    }

    override fun initialiseTimePickerDialog() {
        var timePickerListener =TimePickerDialog.OnTimeSetListener  { _, hour, minute ->
            var min: String ?=null
            if (minute>9){
                min = minute.toString()
            } else {
                min = "0"+minute.toString()
            }
            medTime = hour.toString() + ":"+ min
            med_time_picker.setText(medTime)
        }
        timePickerDialog = TimePickerDialog(this,timePickerListener,  cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE),true)
    }

    /***
     * Show success message if drug entity is successfully add into DB
     */
    override fun showSuccessMessage(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
        finish()
    }

    /**
     * Show error if found any issue while inserting data into DB
     */
    override fun showError(errorMessage: String) {
        Toast.makeText(this,errorMessage,Toast.LENGTH_SHORT).show()
    }

    override fun saveDrugData(drugEntity: DrugEntity) {
        mDrugEntity.drugId = System.currentTimeMillis().toString()
        mDrugEntity.drugName = add_med_name.text.toString()
        mDrugEntity.drugDescription = add_med_desc.text.toString()
        mDrugEntity.drugDoseQuantity = add_dosage_quantity.text.toString()
        mDrugEntity.drugFrequency = add_med_frequency.selectedItem.toString()
        mDrugEntity.drugType = med_type_spinner.selectedItem.toString()
        if (medDate!=null) {
            mDrugEntity.drugAlarmDate = medDate
        }
        if (mDrugEntity.drugTakenOrIgnoreDateList== null) {
            mDrugEntity.drugTakenOrIgnoreDateList = ArrayList()
        }
        if (medTime!=null) {
            mDrugEntity.drugAlarmTime = medTime
        }
        mAddDrugPresenter.addDrugData(mDrugEntity)
    }
    override fun getDateFromPicker() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        medDate = sdf.format(cal.getTime())
        med_date_picker.setText(medDate)
    }

    override fun showDatePicker() {

      datePickerDialog.show()
    }
    override fun setMedAlarm() {
        DrugListAlarmManager.setReminder(this,AddDrugActivity::class.java,mDrugEntity)
    }

    override fun showTimePicker() {
       timePickerDialog.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getBaliActivityComponent().inject(this)
        setContentView(R.layout.activity_add_med)
        mDrugEntity = DrugEntity()
        initialiseDatePickerDialog()
        initialiseTimePickerDialog()
        med_time_picker.setOnClickListener { showTimePicker() }
        med_date_picker.setOnClickListener { showDatePicker() }
        btn_save.setOnClickListener{ saveDrugData(mDrugEntity) }
        add_med_frequency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
               if (position>0) {
                   med_date_picker.visibility = View.VISIBLE
               } else{
                   med_date_picker.visibility = View.GONE
               }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

    }



    override fun onStop() {
        super.onStop()
        mAddDrugPresenter.dropView()
    }

    override fun onStart() {
        super.onStart()
        mAddDrugPresenter.takeView(this)

    }}
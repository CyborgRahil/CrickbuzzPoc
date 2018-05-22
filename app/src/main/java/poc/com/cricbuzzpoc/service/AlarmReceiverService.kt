package poc.com.cricbuzzpoc.service


import android.content.BroadcastReceiver
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import poc.com.cricbuzzpoc.drugDescription.view.DrugDescriptionActivity

import poc.com.cricbuzzpoc.helper.DrugListAlarmManager
import poc.com.cricbuzzpoc.utility.MedicalAppConstants
import javax.inject.Inject

class AlarmReceiverService : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        val drugName = intent!!.getStringExtra(MedicalAppConstants.DRUG_NAME)
        val drugDesc = intent.getStringExtra(MedicalAppConstants.DRUG_DESCRIPTION)
        val drugId = intent.getStringExtra(MedicalAppConstants.DRUG_ID)

        //Trigger the notification
        DrugListAlarmManager.showNotification(context, DrugDescriptionActivity::class.java,
                drugName, drugDesc,drugId)
    }
}
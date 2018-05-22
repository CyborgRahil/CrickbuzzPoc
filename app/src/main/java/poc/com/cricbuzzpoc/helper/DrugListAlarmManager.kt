package poc.com.cricbuzzpoc.helper

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.ComponentName
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.media.RingtoneManager
import android.support.v4.app.NotificationCompat
import poc.com.cricbuzzpoc.R
import poc.com.cricbuzzpoc.data.DrugDataRepository
import poc.com.cricbuzzpoc.data.local.DrugEntity
import poc.com.cricbuzzpoc.service.AlarmReceiverService
import poc.com.cricbuzzpoc.utility.MedicalAppConstants
import poc.com.cricbuzzpoc.utility.Utils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DrugListAlarmManager {



    companion object {
        val TAG = "ALARM_MANAGER"

    fun showNotification(context: Context?, cls: Class<*>, drugName: String, drugDesc: String, drugId: String) {

        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationIntent = Intent(context, cls)
        notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        notificationIntent.putExtra(MedicalAppConstants.DRUG_ID, drugId)

        val stackBuilder = TaskStackBuilder.create(context)
        stackBuilder.addParentStack(cls)
        stackBuilder.addNextIntent(notificationIntent)

        val pendingIntent = stackBuilder.getPendingIntent(
                Utils.getId(drugId), PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(context)

        val notification = builder.setContentTitle("Drug Name: "+drugName)
                .setContentText("Drug Desc. "+ drugDesc)
                .setAutoCancel(true)
                .setSound(alarmSound)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentIntent(pendingIntent).build()

        val notificationManager = context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(Utils.getId(drugId), notification)
    }

    fun setReminder(context: Context, cls: Class<*>, drugEntity: DrugEntity) {

        val calendar = Calendar.getInstance()

        val setCalendar = Calendar.getInstance()
        var alarmTime = drugEntity.drugAlarmTime
        var alarmDate =  drugEntity.drugAlarmDate
        if (alarmDate!=null) {
            var alarmTiming = "$alarmDate $alarmTime"
            val format = SimpleDateFormat("MM/dd/yyyy HH:mm")
            try {
                val date = format.parse(alarmTiming)
                System.out.println(date)
                setCalendar.time = date
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        } else {
            var split = alarmTime!!.split(":")
            setCalendar.timeInMillis = System.currentTimeMillis()
            setCalendar.set(Calendar.HOUR_OF_DAY, split[0].toInt())
            setCalendar.set(Calendar.MINUTE, split[1].toInt())
        }

        setCalendar.set(Calendar.SECOND, 0)
        //        setCalendar.set(Calendar.YEAR, year);
        //        setCalendar.set(Calendar.MONTH, month);
        //        setCalendar.set(Calendar.DAY_OF_MONTH, dom);

        // Cancel already scheduled reminders

        if (setCalendar.before(calendar))
            setCalendar.add(Calendar.DATE, 1)

        // Enable a receiver
        val receiver = ComponentName(context, AlarmReceiverService::class.java)
        val packageManager = context.packageManager

        packageManager.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP)

        val intent = Intent(context, AlarmReceiverService::class.java)
        intent.putExtra(MedicalAppConstants.DRUG_ID, drugEntity.drugId)
        intent.putExtra(MedicalAppConstants.DRUG_NAME, drugEntity.drugDescription)
        intent.putExtra(MedicalAppConstants.DRUG_DESCRIPTION, drugEntity.drugName)

        val pendingIntent = PendingIntent.getBroadcast(context,
                Utils.getId(drugEntity.drugId), intent, 0)

        val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, setCalendar.timeInMillis,
                AlarmManager.INTERVAL_DAY, pendingIntent)
    }

    fun cancelReminder(context: Context, cls: Class<*>,drugEntity: DrugEntity) {

        // Disable a receiver
        val receiver = ComponentName(context, cls)
        val packageManager = context.packageManager

        packageManager.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP)

        val intent = Intent(context, cls)
        val pendingIntent = PendingIntent.getBroadcast(context,
                Utils.getId(drugEntity.drugId), intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)
        pendingIntent.cancel()
    }
    }
}
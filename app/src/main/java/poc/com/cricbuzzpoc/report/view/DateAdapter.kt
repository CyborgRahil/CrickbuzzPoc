package poc.com.cricbuzzpoc.report.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_date_list.view.*
import poc.com.cricbuzzpoc.R
import poc.com.cricbuzzpoc.data.local.DrugReportEntity

class DateAdapter (private val drugDateList: List<DrugReportEntity>, private val isDrugIntake:Boolean) : RecyclerView.Adapter<DateListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DateListViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_date_list, parent, false)
        return DateListViewHolder(view)

    }

    override fun getItemCount(): Int {
        return drugDateList.size

    }

    override fun onBindViewHolder(holder: DateListViewHolder?, position: Int) {
        holder?.bindDateData(drugDateList.get(position),isDrugIntake)
    }

}

class DateListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    /**
     * This method is use to bind data to adapter.
     *
     */
    fun bindDateData(drugData: DrugReportEntity, isMedicineTaken: Boolean) {
        with(drugData) {
            if (isMedicineTaken) {
                if (drugData.isDrugTaken!!) {
                    itemView.tv_date.text = drugData.drugTakenOrIgnoreDate
                }
            } else {
                if (!drugData.isDrugTaken!!) {
                    itemView.tv_date.text = drugData.drugTakenOrIgnoreDate
                }
            }
        }

    }
}
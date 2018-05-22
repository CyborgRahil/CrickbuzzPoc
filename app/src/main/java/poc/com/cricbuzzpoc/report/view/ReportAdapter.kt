package poc.com.cricbuzzpoc.report.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import kotlinx.android.synthetic.main.item_report_list.view.*
import poc.com.cricbuzzpoc.R
import poc.com.cricbuzzpoc.data.local.DrugEntity
import android.support.v7.widget.LinearLayoutManager
import poc.com.cricbuzzpoc.utility.Utils


class ReportAdapter (private val mContext: Context, private val drugListData: List<DrugEntity>, private val isMedicineTaken:Boolean) : RecyclerView.Adapter<ReportListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ReportListViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_report_list, parent, false)
        return ReportListViewHolder(view)

    }

    override fun getItemCount(): Int {
        return drugListData.size

    }

    override fun onBindViewHolder(holder: ReportListViewHolder?, position: Int) {
        holder?.bindForecast(drugListData.get(position),isMedicineTaken, mContext)
    }

}

class ReportListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    /**
     * This method is use to bind data to adapter.
     *
     */
    fun bindForecast(drugData: DrugEntity, isMedicineTaken: Boolean, context: Context) {
        with(drugData) {
            itemView.tv_drug_name.text = drugData.drugName
            itemView.tv_drug_description.text = drugData.drugDescription
            itemView.tv_drug_dose.text = drugData.drugDoseQuantity+ " "+drugData.drugType
            if(isMedicineTaken) {
                itemView.tv_drug_intake_missed_count.text = drugData.drugTaken.toString()
            } else {
                itemView.drug_intake_missed.text = context.getString(R.string.drug_missed_count)
                itemView.tv_drug_intake_missed_count.text = drugData.drugIgnore.toString()
            }
            if (drugData.drugTakenOrIgnoreDateList!!.size==0){
                itemView.tv_list_dates.visibility = View.GONE
            }

            val layoutManager = LinearLayoutManager(context)
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL
            itemView.list_dates_rv.setLayoutManager(layoutManager)
            val adapter = DateAdapter(Utils.getFilteredList(drugData.drugTakenOrIgnoreDateList!!,isMedicineTaken),isMedicineTaken)
            itemView.list_dates_rv.setAdapter(adapter)
        }
        
    }

} 
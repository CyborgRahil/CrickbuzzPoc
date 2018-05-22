package poc.com.cricbuzzpoc.drugList.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_drug_list.view.*
import poc.com.cricbuzzpoc.R
import poc.com.cricbuzzpoc.data.local.DrugEntity
import poc.com.cricbuzzpoc.drugList.listener.DrugItemListener

class DrugListAdapter(private val drugListData: List<DrugEntity>,private val itemClickListener:DrugItemListener) : RecyclerView.Adapter<MedicineListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MedicineListViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_drug_list, parent, false)
        return MedicineListViewHolder(view)

    }

    override fun getItemCount(): Int {
        return drugListData.size

    }

    override fun onBindViewHolder(holder: MedicineListViewHolder?, position: Int) {
        holder?.bindForecast(drugListData.get(position),itemClickListener)
    }

}

class MedicineListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    /**
     * This method is use to bind data to adapter.
     *
     */
    fun bindForecast(drugData: DrugEntity, itemClickListener: DrugItemListener) {
        with(drugData) {
            itemView.tv_drug_name.text = drugData.drugName
            itemView.tv_drug_description.text = drugData.drugDescription
            itemView.tv_drug_dose.text = drugData.drugDoseQuantity+ " "+drugData.drugType
            itemView.tv_drug_timing.text = drugData.drugFrequency+" "+drugData.drugAlarmTime

        }
        itemView.setOnClickListener( { itemClickListener.onDrugItemClick(drugData) })
    }

} 
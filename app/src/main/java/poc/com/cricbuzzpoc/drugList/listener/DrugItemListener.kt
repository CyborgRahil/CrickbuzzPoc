package poc.com.cricbuzzpoc.drugList.listener

import poc.com.cricbuzzpoc.data.local.DrugEntity

interface DrugItemListener {

    fun onDrugItemClick(drugEntity: DrugEntity)
}
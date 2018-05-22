package poc.com.cricbuzzpoc.utility

import poc.com.cricbuzzpoc.data.local.DrugReportEntity

class Utils {
 companion object {

     fun getFilteredList( list:List<DrugReportEntity>,isMedicineIntake:Boolean) : List<DrugReportEntity>{
         var filteredList = ArrayList<DrugReportEntity>()

         for (i in 0..list.size - 1) {
             if (isMedicineIntake) {
                 if (list.get(i).isDrugTaken!!) {
                     filteredList.add(list.get(i))
                 }
             } else {
                 if (!list.get(i).isDrugTaken!!) {
                     filteredList.add(list.get(i))
                 }
             }

         }

         return  filteredList

     }

     fun getId(drugId: String): Int {
       return drugId.substring(drugId.length-5,drugId.length).toInt()
     }


 }

}
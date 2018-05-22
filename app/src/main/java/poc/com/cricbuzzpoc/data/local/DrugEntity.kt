package poc.com.cricbuzzpoc.data.local

import android.arch.persistence.room.*
import poc.com.cricbuzzpoc.data.local.typeConverter.ListConverter


@Entity(tableName = "DrugTable")
class DrugEntity {
    @PrimaryKey
    var drugId:String =""

    var drugName: String ?= null

    var drugDescription: String ?= null

    var drugAlarmTime: String ?= null

    var drugDoseQuantity: String ?= null

    var drugType: String?= null

    var drugFrequency: String ?= null

    var drugAlarmDate: String ? = null

    var drugTaken: Int = 0

    var drugIgnore: Int = 0

    @TypeConverters(ListConverter::class)
    @ColumnInfo(name = "drugTakenOrIgnoreDate")
    var drugTakenOrIgnoreDateList: List<DrugReportEntity>? = null


}

class DrugReportEntity {
    var isDrugTaken: Boolean ?= null
    var drugTakenOrIgnoreDate: String? =null
     constructor(isDrugTaken: Boolean, drugTakenOrIgnoreDate: String){
       this.isDrugTaken = isDrugTaken
        this.drugTakenOrIgnoreDate = drugTakenOrIgnoreDate
     }

}

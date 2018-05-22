package poc.com.cricbuzzpoc.data.local.typeConverter

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import poc.com.cricbuzzpoc.data.local.DrugReportEntity
import java.util.*




class ListConverter {

    var gson = Gson()
    @TypeConverter
    fun objectToList( data:String):List<DrugReportEntity>  {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<DrugReportEntity>>() {

        }.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun listToString(reportOjects: List<DrugReportEntity>): String {
        return gson.toJson(reportOjects)
    }
}
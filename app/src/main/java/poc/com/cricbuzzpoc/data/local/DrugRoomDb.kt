package poc.com.cricbuzzpoc.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import poc.com.cricbuzzpoc.data.local.typeConverter.ListConverter

@Database(entities = arrayOf(DrugEntity::class), version = 1,exportSchema = false)
@TypeConverters(ListConverter::class)
abstract class DrugRoomDb : RoomDatabase() {
    abstract fun getDrugDao() : DrugDao
}
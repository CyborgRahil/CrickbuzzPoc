package poc.com.cricbuzzpoc.di.module

import dagger.Provides
import javax.inject.Singleton
import android.arch.persistence.room.Room
import android.app.Application
import poc.com.cricbuzzpoc.data.local.DrugDao
import poc.com.cricbuzzpoc.data.local.DrugRoomDb
import dagger.Module



@Module
class RoomModule(mApplication: Application) {

    private val roomDbDatabase: DrugRoomDb

    init {
        roomDbDatabase = Room.databaseBuilder(mApplication, DrugRoomDb::class.java, "MedicalApp.db").build()
    }

    @Singleton
    @Provides
    fun providesDrugRoomDb(): DrugRoomDb {
        return roomDbDatabase
    }

    @Singleton
    @Provides
    fun providesDrugDao(roomDbDataBase: DrugRoomDb): DrugDao {
        return roomDbDatabase.getDrugDao()
    }


}
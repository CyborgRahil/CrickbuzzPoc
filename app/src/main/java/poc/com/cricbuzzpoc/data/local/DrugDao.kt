package poc.com.cricbuzzpoc.data.local

import android.arch.persistence.room.*
import io.reactivex.Flowable
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Update
import io.reactivex.Completable
import io.reactivex.Single


@Dao
interface DrugDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDrugData(drugItem: DrugEntity)

    @Query("SELECT * FROM DrugTable")
    fun getDrugList(): Flowable<List<DrugEntity>>

    @Update
    fun updateDrugItem(item: DrugEntity)

    @Delete
    fun deleteDrugItem(item: DrugEntity)

    @Query("SELECT * FROM DrugTable where drugId=:id")
    fun getDrugDetail(id:String): Flowable<DrugEntity>


}
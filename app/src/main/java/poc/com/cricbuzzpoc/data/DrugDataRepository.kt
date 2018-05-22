package poc.com.cricbuzzpoc.data

import poc.com.cricbuzzpoc.data.local.DrugEntity
import io.reactivex.Completable
import io.reactivex.Flowable

 interface DrugDataRepository {

    fun getDrugList(): Flowable<List<DrugEntity>>

    fun updateDrugDetail(item: DrugEntity): Completable

    fun deleteDrugDetail(item: DrugEntity) : Completable

    fun addDrugDetail(item: DrugEntity) : Completable

    fun getDrugDescription(id:String) : Flowable<DrugEntity>


}


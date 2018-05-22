package poc.com.cricbuzzpoc.data

import poc.com.cricbuzzpoc.data.local.DrugDao
import poc.com.cricbuzzpoc.data.local.DrugEntity

import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DrugDataRepositoryImpl
@Inject
constructor(private val drugDao: DrugDao) : DrugDataRepository {

    /***
     * Get list of Drug from DB
     */
    override fun getDrugList(): Flowable<List<DrugEntity>> {
        return drugDao.getDrugList()
    }

    /***
     * Update Drug data in DB
     */
    override fun updateDrugDetail(item: DrugEntity): Completable {
        return Completable.fromCallable { (drugDao.updateDrugItem(item)) }
    }

    /***
     * DELETE Drug data from DB
     */
    override fun deleteDrugDetail(item: DrugEntity): Completable {
        return Completable.fromCallable { drugDao.deleteDrugItem(item) }
    }

    /***
     * Add drug data inside DB
     */
    override fun addDrugDetail(item: DrugEntity): Completable {
        var result = Completable.fromCallable({ drugDao.addDrugData(item) })
        return result
    }

    /***
     * Get drug data based on Drug Id
     */
    override fun getDrugDescription(id: String): Flowable<DrugEntity> {
        return drugDao.getDrugDetail(id)
    }


}
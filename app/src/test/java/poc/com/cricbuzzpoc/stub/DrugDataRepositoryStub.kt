package poc.com.cricbuzzpoc.stub

import io.reactivex.Completable
import io.reactivex.Flowable
import poc.com.cricbuzzpoc.data.DrugDataRepository
import poc.com.cricbuzzpoc.data.local.DrugEntity

class DrugDataRepositoryStub (
        private val drugEntity: DrugEntity) : DrugDataRepository{
    override fun getDrugList(): Flowable<List<DrugEntity>> {
        return Flowable.just(listOf(drugEntity))
    }

    override fun updateDrugDetail(item: DrugEntity): Completable {
        return Completable.complete()
    }

    override fun deleteDrugDetail(item: DrugEntity): Completable {
        return Completable.complete()
    }

    override fun addDrugDetail(item: DrugEntity): Completable {
        return Completable.complete()
    }

    override fun getDrugDescription(id: String): Flowable<DrugEntity> {
        if (id.equals(drugEntity.drugId)) {
            return Flowable.just((drugEntity))
        } else {
            return Flowable.just(DrugEntity())
        }
    }


}

package poc.com.cricbuzzpoc.addDrug.domain.usecase

import io.reactivex.Completable
import poc.com.cricbuzzpoc.data.DrugDataRepository
import io.reactivex.Single
import poc.com.cricbuzzpoc.data.local.DrugEntity
import javax.inject.Inject

interface AddDrugUseCase {

    fun execute(drugEntity: DrugEntity) : Completable

}

class AddDrugUseCaseImpl
@Inject
constructor(private val drugDataRepository:DrugDataRepository) : AddDrugUseCase {
    override fun execute(drugEntity: DrugEntity): Completable {
       return drugDataRepository.addDrugDetail(drugEntity)

    }
}
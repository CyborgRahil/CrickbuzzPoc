package poc.com.cricbuzzpoc.drugDescription.domain.usecase

import io.reactivex.Completable
import poc.com.cricbuzzpoc.data.DrugDataRepository
import poc.com.cricbuzzpoc.data.local.DrugEntity
import javax.inject.Inject

interface DrugUpdateUseCase {
    fun execute(drugEntity: DrugEntity) : Completable

}

class DrugUpdateUseCaseImpl
@Inject
constructor(private val drugDataRepository: DrugDataRepository) : DrugUpdateUseCase {
    override fun execute(drugEntity: DrugEntity): Completable {
        return drugDataRepository.updateDrugDetail(drugEntity)
    }
}
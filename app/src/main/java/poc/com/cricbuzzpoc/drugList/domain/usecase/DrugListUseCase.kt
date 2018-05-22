package poc.com.cricbuzzpoc.drugList.domain.usecase

import poc.com.cricbuzzpoc.data.DrugDataRepository
import poc.com.cricbuzzpoc.data.local.DrugEntity
import io.reactivex.Flowable
import javax.inject.Inject

interface DrugListUseCase {

    fun execute() : Flowable<List<DrugEntity>>
}


class  DrugListUseCaseImpl
  @Inject
  constructor(private val repository: DrugDataRepository) : DrugListUseCase {
    override fun execute(): Flowable<List<DrugEntity>> {
        return repository.getDrugList()

    }
}
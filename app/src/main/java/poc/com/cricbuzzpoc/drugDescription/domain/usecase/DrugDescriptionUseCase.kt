package poc.com.cricbuzzpoc.addDrug.domain.usecase

import io.reactivex.Flowable
import poc.com.cricbuzzpoc.data.DrugDataRepository
import io.reactivex.Single
import poc.com.cricbuzzpoc.data.local.DrugEntity
import javax.inject.Inject


interface DrugDescriptionUseCase {

    fun execute(id:String) : Flowable<DrugEntity>

}

class DrugDescriptionUseCaseImpl
@Inject
constructor(private val drugDataRepository:DrugDataRepository) : DrugDescriptionUseCase {
    override fun execute(id:String): Flowable<DrugEntity> {
        return drugDataRepository.getDrugDescription(id)
    }
}
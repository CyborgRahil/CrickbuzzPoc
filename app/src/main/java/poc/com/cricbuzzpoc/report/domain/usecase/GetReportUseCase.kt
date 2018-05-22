package poc.com.cricbuzzpoc.report.domain.usecase

import io.reactivex.Flowable
import poc.com.cricbuzzpoc.data.DrugDataRepository
import poc.com.cricbuzzpoc.data.local.DrugEntity
import javax.inject.Inject

interface GetReportUseCase {
    fun execute() : Flowable<List<DrugEntity>>
}

class GetReportUseCaseImpl
@Inject
constructor(private val drugDataRepository: DrugDataRepository):GetReportUseCase {
    override fun execute(): Flowable<List<DrugEntity>> {
        return drugDataRepository.getDrugList()
    }

}
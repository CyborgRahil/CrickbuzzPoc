package poc.com.cricbuzzpoc.drugDescription

import org.junit.Test
import poc.com.cricbuzzpoc.testUtility.TestUtililty
import poc.com.cricbuzzpoc.addDrug.domain.usecase.DrugDescriptionUseCaseImpl
import poc.com.cricbuzzpoc.stub.DrugDataRepositoryStub

class DrugDescriptionUseCaseTest {

    @Test
    fun `execute should return drug Entity if ID match`() {
        val drugEntity = TestUtililty.createEntity()
        val drugDataRepository = DrugDataRepositoryStub(drugEntity)
        val useCase = DrugDescriptionUseCaseImpl(drugDataRepository)

        val result = useCase.execute("123").test()

        result.assertNoErrors()
        result
                .assertValue { it.drugName == drugEntity.drugName }
                .assertValue { it.drugDescription == drugEntity.drugDescription }
                .assertValue { it.drugId == drugEntity.drugId }
                .assertValue { it.drugDoseQuantity == drugEntity.drugDoseQuantity }
                .assertValue { it.drugAlarmTime == drugEntity.drugAlarmTime }

    }

    @Test
    fun `execute should return blank drug Entity if ID does not match`() {
        val drugEntity = TestUtililty.createEntity()
        val drugDataRepository = DrugDataRepositoryStub(drugEntity)
        val useCase = DrugDescriptionUseCaseImpl(drugDataRepository)

        val result = useCase.execute("321").test()

        result.assertNoErrors()
        result
                .assertValue { it.drugName == null }
                .assertValue { it.drugDescription == null }
                .assertValue { it.drugId == ""}
                .assertValue { it.drugDoseQuantity == null }
                .assertValue { it.drugAlarmTime == null }

    }

}
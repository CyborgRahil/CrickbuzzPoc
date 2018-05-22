package poc.com.cricbuzzpoc.drugList

import org.junit.Test
import poc.com.cricbuzzpoc.testUtility.TestUtililty
import poc.com.cricbuzzpoc.drugList.domain.usecase.DrugListUseCaseImpl
import poc.com.cricbuzzpoc.stub.DrugDataRepositoryStub

class DrugListUseCaseTest {
    @Test
    fun `execute should return drug list`() {
        val drugEntity = TestUtililty.createEntity()
        val drugDataRepository = DrugDataRepositoryStub(drugEntity)
        val useCase = DrugListUseCaseImpl(drugDataRepository)

        val result = useCase.execute().test()

        result.assertNoErrors()
        result
                .assertValue { it[0].drugName == drugEntity.drugName }
                .assertValue { it[0].drugDescription == drugEntity.drugDescription }
                .assertValue { it[0].drugId == drugEntity.drugId }
                .assertValue { it[0].drugDoseQuantity == drugEntity.drugDoseQuantity }
                .assertValue { it[0].drugAlarmTime == drugEntity.drugAlarmTime }

    }
}
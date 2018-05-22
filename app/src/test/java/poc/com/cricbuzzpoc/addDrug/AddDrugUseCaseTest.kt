package poc.com.cricbuzzpoc.addDrug

import org.junit.Test
import poc.com.cricbuzzpoc.testUtility.TestUtililty
import poc.com.cricbuzzpoc.addDrug.domain.usecase.AddDrugUseCaseImpl
import poc.com.cricbuzzpoc.stub.DrugDataRepositoryStub

class AddDrugUseCaseTest {

    @Test
    fun `execute should return Completable`() {
        val drugEntity = TestUtililty.createEntity()
        val drugDataRepository = DrugDataRepositoryStub(drugEntity)
        val useCase = AddDrugUseCaseImpl(drugDataRepository)

        val result = useCase.execute(drugEntity).test()

        result.assertNoErrors()
        result.assertComplete()
    }
}
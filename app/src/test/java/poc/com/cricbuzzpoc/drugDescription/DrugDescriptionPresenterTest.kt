package poc.com.cricbuzzpoc.drugDescription


import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

import poc.com.cricbuzzpoc.testUtility.TestUtililty
import poc.com.cricbuzzpoc.addDrug.domain.usecase.DrugDescriptionUseCase
import poc.com.cricbuzzpoc.addDrug.domain.usecase.DrugDescriptionUseCaseImpl
import poc.com.cricbuzzpoc.data.DrugDataRepository
import poc.com.cricbuzzpoc.data.local.DrugEntity
import poc.com.cricbuzzpoc.drugDescription.domain.usecase.DrugUpdateUseCase
import poc.com.cricbuzzpoc.drugDescription.domain.usecase.DrugUpdateUseCaseImpl
import poc.com.cricbuzzpoc.drugDescription.view.DrugDescriptionContract
import poc.com.cricbuzzpoc.drugDescription.view.DrugDescriptionPresenter
import poc.com.cricbuzzpoc.stub.DrugDataRepositoryStub
import poc.com.cricbuzzpoc.testUtility.TestSchedulerProvider

class DrugDescriptionPresenterTest {

    lateinit var drugDescriptionUseCase:DrugDescriptionUseCase


    lateinit var drugUpdateUseCase:DrugUpdateUseCase
    @Mock
    lateinit var drugDataRepository : DrugDataRepository

    @Mock
    lateinit var drugDescriptionView:DrugDescriptionContract.DrugDescriptionView

    lateinit var drugDescriptionPresenter: DrugDescriptionPresenter

    var drugEntity: DrugEntity = TestUtililty.createEntity()
    lateinit var testSchedulerProvider: TestSchedulerProvider

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        testSchedulerProvider = TestSchedulerProvider()
        drugDescriptionUseCase = DrugDescriptionUseCaseImpl(drugDataRepository)
        drugUpdateUseCase = DrugUpdateUseCaseImpl(drugDataRepository)
        drugDescriptionPresenter = DrugDescriptionPresenter(drugDescriptionUseCase,drugUpdateUseCase,testSchedulerProvider)

    }

    @Test
    fun `get drug description from db`() {
        Mockito.`when`(drugDataRepository.getDrugDescription("123")).thenReturn(Flowable.just((drugEntity)))
        drugDescriptionPresenter.takeView(drugDescriptionView)
        drugDescriptionPresenter.getUserMedicineData("123")
        testSchedulerProvider.testScheduler.triggerActions()
        verify(drugDescriptionView).updateView(any())
    }

    @Test
    fun `get error while getting data using from db`() {
        Mockito.`when`(drugDataRepository.getDrugDescription("321")).thenReturn(Flowable.error(Throwable()))
        drugDescriptionPresenter.takeView(drugDescriptionView)
        drugDescriptionPresenter.getUserMedicineData("321")
        testSchedulerProvider.testScheduler.triggerActions()
        verify(drugDescriptionView).showError(any())
    }
}
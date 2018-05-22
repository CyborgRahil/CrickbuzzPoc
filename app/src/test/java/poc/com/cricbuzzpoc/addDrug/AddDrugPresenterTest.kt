package poc.com.cricbuzzpoc.addDrug


import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

import poc.com.cricbuzzpoc.testUtility.TestUtililty
import poc.com.cricbuzzpoc.addDrug.domain.usecase.AddDrugUseCase
import poc.com.cricbuzzpoc.addDrug.domain.usecase.AddDrugUseCaseImpl
import poc.com.cricbuzzpoc.addDrug.view.AddDrugContract
import poc.com.cricbuzzpoc.addDrug.view.AddDrugPresenter
import poc.com.cricbuzzpoc.data.DrugDataRepository
import poc.com.cricbuzzpoc.data.local.DrugEntity
import poc.com.cricbuzzpoc.testUtility.TestSchedulerProvider

class AddDrugPresenterTest {
    @Mock
    lateinit var drugDataRepository: DrugDataRepository

    @Mock
    lateinit var mAddDrugView: AddDrugContract.AddDrugView

    lateinit var mAddDrugPresenter: AddDrugPresenter

    lateinit var addDrugUseCase: AddDrugUseCase

    var drugEntity:DrugEntity= TestUtililty.createEntity()
    lateinit var testSchedulerProvider: TestSchedulerProvider

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        testSchedulerProvider = TestSchedulerProvider()
        addDrugUseCase = AddDrugUseCaseImpl(drugDataRepository)

        mAddDrugPresenter = AddDrugPresenter(addDrugUseCase,testSchedulerProvider)
    }

    @Test
    fun `add drug data into db`() {
        Mockito.`when`(drugDataRepository.addDrugDetail(drugEntity)).thenReturn(Completable.complete())
        mAddDrugPresenter.takeView(mAddDrugView)
        mAddDrugPresenter.addDrugData(drugEntity)
        testSchedulerProvider.testScheduler.triggerActions()
        verify(mAddDrugView).showSuccessMessage(any())

    }

    @Test
    fun `get error while add drug data into db`() {
        Mockito.`when`(drugDataRepository.addDrugDetail(drugEntity)).thenReturn(Completable.error(Throwable()))
        mAddDrugPresenter.takeView(mAddDrugView)
        mAddDrugPresenter.addDrugData(drugEntity)
        testSchedulerProvider.testScheduler.triggerActions()
        verify(mAddDrugView).showError(any())

    }

}
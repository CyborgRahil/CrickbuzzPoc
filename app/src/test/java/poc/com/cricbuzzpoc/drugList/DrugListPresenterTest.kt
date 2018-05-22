package poc.com.cricbuzzpoc.drugList

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import poc.com.cricbuzzpoc.testUtility.TestUtililty
import poc.com.cricbuzzpoc.data.DrugDataRepository
import poc.com.cricbuzzpoc.data.local.DrugEntity
import poc.com.cricbuzzpoc.drugList.domain.usecase.DrugListUseCase
import poc.com.cricbuzzpoc.drugList.domain.usecase.DrugListUseCaseImpl
import poc.com.cricbuzzpoc.drugList.view.DrugListContract
import poc.com.cricbuzzpoc.drugList.view.DrugListPresenter
import poc.com.cricbuzzpoc.testUtility.TestSchedulerProvider

class DrugListPresenterTest {

    lateinit var drugListUseCase: DrugListUseCase

    lateinit var testSchedulerProvider: TestSchedulerProvider
    @Mock
    lateinit var drugDataRepository: DrugDataRepository

    @Mock
    lateinit var drugListView: DrugListContract.DrugListView

    lateinit var drugListPresenter: DrugListPresenter

    var drugEntity: DrugEntity = TestUtililty.createEntity()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        testSchedulerProvider = TestSchedulerProvider()
        drugListUseCase = DrugListUseCaseImpl(drugDataRepository)
        drugListPresenter = DrugListPresenter(drugListUseCase,testSchedulerProvider)
    }

    @Test
    fun `get drug list from db`() {
        Mockito.`when`(drugDataRepository.getDrugList()).thenReturn(Flowable.just(listOf(drugEntity)))
        drugListPresenter.takeView(drugListView)
        drugListPresenter.getDrugList()
        testSchedulerProvider.testScheduler.triggerActions()
        verify(drugListView).populateRecyclerView(any())
    }

    @Test
    fun `get error while getting list from db`() {
        Mockito.`when`(drugDataRepository.getDrugList()).thenReturn(Flowable.error(Throwable()))
        drugListPresenter.takeView(drugListView)
        drugListPresenter.getDrugList()
        testSchedulerProvider.testScheduler.triggerActions()
        verify(drugListView).showError(any())
    }

    @Test
    fun `get blank list while getting list from db`() {
        Mockito.`when`(drugDataRepository.getDrugList()).thenReturn(Flowable.just(emptyList()))
        drugListPresenter.takeView(drugListView)
        drugListPresenter.getDrugList()
        testSchedulerProvider.testScheduler.triggerActions()
        verify(drugListView).showError(any())
    }

}
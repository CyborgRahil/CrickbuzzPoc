package poc.com.cricbuzzpoc.drugReport

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import poc.com.cricbuzzpoc.testUtility.TestUtililty
import poc.com.cricbuzzpoc.data.DrugDataRepository
import poc.com.cricbuzzpoc.data.local.DrugEntity
import poc.com.cricbuzzpoc.report.domain.usecase.GetReportUseCase
import poc.com.cricbuzzpoc.report.domain.usecase.GetReportUseCaseImpl
import poc.com.cricbuzzpoc.report.view.ReportContract
import poc.com.cricbuzzpoc.report.view.ReportPresenter
import poc.com.cricbuzzpoc.testUtility.TestSchedulerProvider

class DrugReportPresenterTest {
    lateinit var reportUseCase: GetReportUseCase


    @Mock
    lateinit var drugDataRepository: DrugDataRepository

    @Mock
    lateinit var reportView: ReportContract.ReportView

    lateinit var reportPresenter: ReportPresenter

    var drugEntity: DrugEntity = TestUtililty.createEntity()
    lateinit var testSchedulerProvider: TestSchedulerProvider

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        testSchedulerProvider = TestSchedulerProvider()
        reportUseCase = GetReportUseCaseImpl(drugDataRepository)
        reportPresenter = ReportPresenter(reportUseCase,testSchedulerProvider)
    }

    @Test
    fun `get drug list from db`() {
        Mockito.`when`(drugDataRepository.getDrugList()).thenReturn(Flowable.just(listOf(drugEntity)))
        reportPresenter.takeView(reportView)
        reportPresenter.getDrugList()
        testSchedulerProvider.testScheduler.triggerActions()
        verify(reportView).populateRecyclerView(any())
    }

    @Test
    fun `get error while getting list from db`() {
        Mockito.`when`(drugDataRepository.getDrugList()).thenReturn(Flowable.error(Throwable()))
        reportPresenter.takeView(reportView)
        reportPresenter.getDrugList()
        testSchedulerProvider.testScheduler.triggerActions()
        verify(reportView).showError(any())
    }

    @Test
    fun `get blank list while getting list from db`() {
        Mockito.`when`(drugDataRepository.getDrugList()).thenReturn(Flowable.just(emptyList()))
        reportPresenter.takeView(reportView)
        reportPresenter.getDrugList()
        testSchedulerProvider.testScheduler.triggerActions()
        verify(reportView).showError(any())
    }
}
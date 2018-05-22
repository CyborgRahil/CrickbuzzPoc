package poc.com.cricbuzzpoc.report.view

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import poc.com.cricbuzzpoc.report.domain.usecase.GetReportUseCase
import poc.com.cricbuzzpoc.utility.SchedulerProvider
import javax.inject.Inject

class ReportPresenter
@Inject
constructor(private val reportUseCase: GetReportUseCase,private val schedulerProvider: SchedulerProvider) : ReportContract.Presenter
{
    var mView: ReportContract.ReportView?=null
    val mDisposable: CompositeDisposable = CompositeDisposable()
    override fun takeView(view: ReportContract.ReportView) {
       mView = view
    }

    override fun dropView() {
        mDisposable.dispose()
        mView = null
    }

    override fun getDrugList() {
        mDisposable.add(reportUseCase.execute()
                .subscribeOn(schedulerProvider.ioScheduler())
                .observeOn(schedulerProvider.uiScheduler())
                .subscribe({  if (it.size>0) {
                    mView!!.populateRecyclerView(it)
                } else {
                    mView!!.showError("Please add a medicine from home screen.")
                }
                }, {mView!!.showError("Reports are not available.")})
        )
    }
}
package poc.com.cricbuzzpoc.drugList.view

import io.reactivex.disposables.CompositeDisposable
import poc.com.cricbuzzpoc.data.local.DrugEntity
import poc.com.cricbuzzpoc.drugList.domain.usecase.DrugListUseCase
import poc.com.cricbuzzpoc.utility.SchedulerProvider
import javax.inject.Inject

class DrugListPresenter
@Inject
constructor(private val drugListUseCase: DrugListUseCase,private val schedulerProvider:SchedulerProvider) :
DrugListContract.Presenter {
    override fun drugReportGen(isMedicineIntake: Boolean) {
        mView!!.drugReportUi(isMedicineIntake)
    }

    override fun openDrugListItem(drugEntity: DrugEntity) {
        mView!!.showDrugDetailsUi(drugEntity.drugId)
    }


    var mView: DrugListContract.DrugListView?=null
    var mDisposable: CompositeDisposable ?= null

    override fun takeView(view: DrugListContract.DrugListView) {
           mView = view
           mDisposable = CompositeDisposable()
    }
    override fun dropView() {
        mDisposable!!.dispose()
        mView=null
    }

    /**
     * get list of drug data from DB
     */
    override fun getDrugList() {
      mDisposable!!.add(drugListUseCase.execute()
              .subscribeOn(schedulerProvider.ioScheduler())
              .observeOn(schedulerProvider.uiScheduler())
              .subscribe({  if (it.size>0) {
                  mView!!.populateRecyclerView(it)
              } else {
                  mView!!.showError("Please add a medicine.")
              }
              }, {mView!!.showError("Please try again.")})
      )
    }


}
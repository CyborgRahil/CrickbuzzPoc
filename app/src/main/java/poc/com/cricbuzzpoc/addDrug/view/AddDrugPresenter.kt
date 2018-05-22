package poc.com.cricbuzzpoc.addDrug.view

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import poc.com.cricbuzzpoc.data.DrugDataRepository
import poc.com.cricbuzzpoc.addDrug.domain.usecase.AddDrugUseCase
import poc.com.cricbuzzpoc.data.local.DrugEntity
import poc.com.cricbuzzpoc.drugList.view.DrugListContract
import poc.com.cricbuzzpoc.utility.SchedulerProvider
import javax.inject.Inject

class AddDrugPresenter
@Inject
constructor(private val addDrugUseCase: AddDrugUseCase, private val schedulerProvider: SchedulerProvider) : AddDrugContract.Presenter {
    private var mView: AddDrugContract.AddDrugView? = null
    private val mDisposable: CompositeDisposable = CompositeDisposable()

    /**
     * Add drug data into DB
     */
    override fun addDrugData(drugEntity: DrugEntity) {
        if (drugEntity.drugAlarmTime == null || drugEntity.drugName == null || drugEntity.drugDoseQuantity == null || drugEntity.drugDescription == null) {
            mView!!.showError("Please enter all details")
            return
        }

            mDisposable.add(addDrugUseCase.execute(drugEntity)
                    .subscribeOn(schedulerProvider.ioScheduler())
                    .observeOn(schedulerProvider.uiScheduler())
                    .subscribe({
                        mView!!.setMedAlarm()
                        mView!!.showSuccessMessage("Your information is added successfully")
                    }, { mView!!.showError("Please try again") }

                    )
            )
    }

    override fun takeView(view: AddDrugContract.AddDrugView) {
        mView = view
    }

    override fun dropView() {
        mDisposable.dispose()
        mView = null
    }

}
package poc.com.cricbuzzpoc.drugDescription.view

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import poc.com.cricbuzzpoc.data.DrugDataRepository
import poc.com.cricbuzzpoc.addDrug.domain.usecase.AddDrugUseCase
import poc.com.cricbuzzpoc.addDrug.domain.usecase.DrugDescriptionUseCase
import poc.com.cricbuzzpoc.data.local.DrugEntity
import poc.com.cricbuzzpoc.drugDescription.domain.usecase.DrugUpdateUseCase
import poc.com.cricbuzzpoc.utility.SchedulerProvider
import java.util.function.Consumer
import javax.inject.Inject

class DrugDescriptionPresenter
@Inject
constructor(private val drugDescriptionUseCase: DrugDescriptionUseCase, private val drugUpdateUseCase: DrugUpdateUseCase, private val schedulerProvider: SchedulerProvider) : DrugDescriptionContract.Presenter {
    var mView: DrugDescriptionContract.DrugDescriptionView? = null
    val mDisposable: CompositeDisposable = CompositeDisposable()
    override fun getUserMedicineData(drugId: String) {
        mDisposable.add(drugDescriptionUseCase.execute(drugId)
                .subscribeOn(schedulerProvider.ioScheduler())
                .observeOn(schedulerProvider.uiScheduler())
                .subscribe({
                    if (it != null) {
                        mView!!.updateView(it)
                    }
                }, {
                    mView!!.showError("Please try again")
                }

                )
        )
    }

    override fun updateDrugData(drugEntity: DrugEntity) {
        mDisposable.add(drugUpdateUseCase.execute(drugEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ mView!!.showSuccessMessage("Your data is successfully updated.") },
                        { mView!!.showError("Please try again.") })
        )
    }

    override fun takeView(view: DrugDescriptionContract.DrugDescriptionView) {
        mView = view
    }


    override fun dropView() {
        mDisposable.dispose()
        mView = null
    }


}
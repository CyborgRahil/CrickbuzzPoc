package poc.com.cricbuzzpoc.di.module

import poc.com.cricbuzzpoc.base.BaseActivity
import poc.com.cricbuzzpoc.data.DrugDataRepository
import poc.com.cricbuzzpoc.drugList.domain.usecase.DrugListUseCase
import poc.com.cricbuzzpoc.drugList.domain.usecase.DrugListUseCaseImpl
import poc.com.cricbuzzpoc.drugList.view.DrugListPresenter
import poc.com.cricbuzzpoc.addDrug.domain.usecase.AddDrugUseCase
import poc.com.cricbuzzpoc.addDrug.domain.usecase.AddDrugUseCaseImpl

import dagger.Module
import dagger.Provides
import poc.com.cricbuzzpoc.addDrug.domain.usecase.DrugDescriptionUseCase
import poc.com.cricbuzzpoc.addDrug.domain.usecase.DrugDescriptionUseCaseImpl
import poc.com.cricbuzzpoc.addDrug.view.AddDrugPresenter
import poc.com.cricbuzzpoc.drugDescription.domain.usecase.DrugUpdateUseCase
import poc.com.cricbuzzpoc.drugDescription.domain.usecase.DrugUpdateUseCaseImpl
import poc.com.cricbuzzpoc.drugDescription.view.DrugDescriptionPresenter
import poc.com.cricbuzzpoc.report.domain.usecase.GetReportUseCase
import poc.com.cricbuzzpoc.report.domain.usecase.GetReportUseCaseImpl
import poc.com.cricbuzzpoc.report.view.ReportPresenter
import poc.com.cricbuzzpoc.utility.AppSchedulerProvider
import poc.com.cricbuzzpoc.utility.SchedulerProvider
import javax.inject.Singleton


@Module
class ActivityModule(private val baseActivity: BaseActivity) {

    @Provides
    internal fun provideDrugListUseCase(repository: DrugDataRepository): DrugListUseCase {
        return DrugListUseCaseImpl(repository)
    }

    @Provides
    fun provideDrugListPresenter(useCase: DrugListUseCase, schedulerProvider: SchedulerProvider): DrugListPresenter {
        return DrugListPresenter(useCase,schedulerProvider)
    }


    @Provides
    internal fun provideAddDrugUseCase(repository: DrugDataRepository): AddDrugUseCase {
        return AddDrugUseCaseImpl(repository)
    }

    @Provides
    internal fun provideAddDrugPresenter(useCase: AddDrugUseCase, schedulerProvider: SchedulerProvider): AddDrugPresenter {
        return AddDrugPresenter(useCase,schedulerProvider)
    }

    @Provides
    internal fun provideDrugUpdateUseCase(repository: DrugDataRepository): DrugUpdateUseCase {
        return DrugUpdateUseCaseImpl(repository)
    }

    @Provides
    internal fun provideDrugDescriptionUseCase(repository: DrugDataRepository): DrugDescriptionUseCase {
        return DrugDescriptionUseCaseImpl(repository)
    }

    @Provides
    internal fun provideDrugDescriptionPresenter(useCase: DrugDescriptionUseCase, drugUpdateUseCase: DrugUpdateUseCase, schedulerProvider: SchedulerProvider): DrugDescriptionPresenter {
        return DrugDescriptionPresenter(useCase, drugUpdateUseCase,schedulerProvider)
    }

    @Provides
    internal fun provideGetReportUseCase(repository: DrugDataRepository): GetReportUseCase {
        return GetReportUseCaseImpl(repository)
    }

    @Provides
    internal fun provideReportPresenter(useCase: GetReportUseCase, schedulerProvider: SchedulerProvider): ReportPresenter {
        return ReportPresenter(useCase,schedulerProvider)
    }
}
package poc.com.cricbuzzpoc.di.component

import poc.com.cricbuzzpoc.di.ActivityScope
import poc.com.cricbuzzpoc.di.module.ActivityModule
import poc.com.cricbuzzpoc.drugList.view.DrugListActivity
import dagger.Component
import poc.com.cricbuzzpoc.addDrug.view.AddDrugActivity
import poc.com.cricbuzzpoc.drugDescription.view.DrugDescriptionActivity
import poc.com.cricbuzzpoc.report.view.ReportActivity


@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))

interface ActivityComponent {

    fun inject(activity: DrugListActivity)
    fun inject(activity: DrugDescriptionActivity)
    fun inject(activity: AddDrugActivity)
    fun inject(activity: ReportActivity)

}
package poc.com.cricbuzzpoc.di.component

import poc.com.cricbuzzpoc.MedicalApp
import poc.com.cricbuzzpoc.data.DrugDataRepository
import poc.com.cricbuzzpoc.data.local.DrugDao
import poc.com.cricbuzzpoc.di.ApplicationScope
import poc.com.cricbuzzpoc.di.module.*
import dagger.Component
import poc.com.cricbuzzpoc.utility.SchedulerProvider
import javax.inject.Singleton


@Singleton
@ApplicationScope
@Component(modules = arrayOf(ApplicationModule::class,RepositoryModule::class, RoomModule::class))
interface ApplicationComponent {

    fun MedicalApp(): MedicalApp

    fun DrugDataRepository(): DrugDataRepository

    fun DrugDao(): DrugDao

    fun SchedulerProvider():SchedulerProvider

}

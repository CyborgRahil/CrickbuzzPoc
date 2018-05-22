package poc.com.cricbuzzpoc.di.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import poc.com.cricbuzzpoc.MedicalApp

import dagger.Module
import dagger.Provides
import poc.com.cricbuzzpoc.utility.AppSchedulerProvider
import poc.com.cricbuzzpoc.utility.SchedulerProvider
import javax.inject.Singleton



@Module
class ApplicationModule(private val mainApplication: MedicalApp) {

    @Provides
    @Singleton
    internal fun provideMainApplication(): MedicalApp {
        return mainApplication
    }

    @Provides
    @Singleton
    internal fun provideSharedPreferences(mainApplication: MedicalApp): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(mainApplication)
    }

    @Provides
    @Singleton
    internal fun provideContext(): Context {
        return mainApplication
    }

    @Provides
    @Singleton
    fun provideSchedulerProvider() : SchedulerProvider = AppSchedulerProvider()

}

package poc.com.cricbuzzpoc.di.module

import poc.com.cricbuzzpoc.data.DrugDataRepository
import poc.com.cricbuzzpoc.data.DrugDataRepositoryImpl
import poc.com.cricbuzzpoc.data.local.DrugDao

import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideDrugDataRepository(drugDao: DrugDao): DrugDataRepository {
        return DrugDataRepositoryImpl(drugDao)
    }

}

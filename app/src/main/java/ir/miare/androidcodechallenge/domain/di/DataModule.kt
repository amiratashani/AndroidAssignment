package ir.miare.androidcodechallenge.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.miare.androidcodechallenge.data.repository.FootballRepositoryImp
import ir.miare.androidcodechallenge.domain.repository.FootballRepository

@InstallIn(SingletonComponent::class)
@Module
interface DataModule {

    @Binds
    fun bindsFootballRepository(footballRepositoryImp: FootballRepositoryImp): FootballRepository

}
package com.shahbozbek.valyutakursi.data.di

import com.shahbozbek.valyutakursi.data.api.ApiInterface
import com.shahbozbek.valyutakursi.data.datasource.remote.RemoteDataSource
import com.shahbozbek.valyutakursi.data.datasource.remote.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @[Provides Singleton]
    fun provideRemoteDataSource(api: ApiInterface): RemoteDataSource {
        return RemoteDataSourceImpl(api)
    }
}

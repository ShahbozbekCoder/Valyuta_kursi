package com.shahbozbek.valyutakursi.di

import com.shahbozbek.valyutakursi.data.api.ApiInterface
import com.shahbozbek.valyutakursi.data.datasource.remote.RemoteDataSource
import com.shahbozbek.valyutakursi.data.repository.CurrencyRepositoryImpl
import com.shahbozbek.valyutakursi.domain.repository.CurrencyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object BaseModule {

    @[Provides Singleton]
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiInterface =
        retrofit.create(ApiInterface::class.java)

    @Provides
    fun provideCurrencyRepository(impl: CurrencyRepositoryImpl): CurrencyRepository = impl

}
private const val BASE_URL = "https://cbu.uz/uz/"
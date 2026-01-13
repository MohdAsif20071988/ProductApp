package com.example.productapp.di

import com.example.productapp.common.BASE_URL
import com.example.productapp.data.remote.ProductAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun getBaseUrl(): String = BASE_URL

    @Singleton
    @Provides
    fun getRetrofit(baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun getProductAPI(retrofit: Retrofit): ProductAPI = retrofit.create(ProductAPI::class.java)
}
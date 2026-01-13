package com.example.productapp.di

import com.example.productapp.data.repository.ProductRepositoryImpl
import com.example.productapp.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun getProductRepository(repositoryImpl: ProductRepositoryImpl): ProductRepository
}
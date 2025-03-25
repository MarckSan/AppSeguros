package com.example.appseguros.di

import android.os.Parcel
import android.os.Parcelable
import com.example.appseguros.data.repository.InsuranceRepository
import com.example.appseguros.data.repository.InsuranceRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule() : Parcelable {
    @Binds
    @Singleton
    abstract fun bindInsuranceRepository(insuranceRepositoryImpl: InsuranceRepositoryImpl): InsuranceRepository
}
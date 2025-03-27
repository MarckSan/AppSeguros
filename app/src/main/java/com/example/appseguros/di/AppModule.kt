package com.example.appseguros.di

import android.os.Parcel
import android.os.Parcelable
import com.example.appseguros.data.repository.FirebaseInsuranceRepositoryImpl
import com.example.appseguros.data.repository.InsuranceRepository
import com.google.firebase.database.FirebaseDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Provides
    @Singleton
    fun provideInsuranceRepository(database: FirebaseDatabase): InsuranceRepository {
        return FirebaseInsuranceRepositoryImpl(database)
    }
}
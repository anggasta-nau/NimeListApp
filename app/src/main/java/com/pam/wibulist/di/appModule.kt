package com.pam.wibulist.di

import com.google.firebase.auth.FirebaseAuth
import com.pam.wibulist.data.authRepository
import com.pam.wibulist.data.authRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object appModule {

    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()


    @Provides
    @Singleton
    fun providesRepositoryImpl(firebaseAuth: FirebaseAuth): authRepository {
        return authRepositoryImpl(firebaseAuth)
    }
}
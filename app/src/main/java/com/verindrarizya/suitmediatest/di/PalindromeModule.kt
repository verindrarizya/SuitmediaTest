package com.verindrarizya.suitmediatest.di

import com.verindrarizya.suitmediatest.domain.usecase.PalindromeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PalindromeModule {

    @Provides
    fun providePalindromeUseCase(): PalindromeUseCase {
        return PalindromeUseCase()
    }
}
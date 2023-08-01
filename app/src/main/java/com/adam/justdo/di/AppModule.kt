package com.adam.justdo.di

import com.adam.justdo.domain.usecase.Interactor
import com.adam.justdo.domain.usecase.UseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideUseCase(interactor: Interactor): UseCase
}
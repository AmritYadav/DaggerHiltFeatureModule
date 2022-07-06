package com.example.daggerhiltdynamicfeature.di

import android.app.Activity
import android.app.Application
import androidx.hilt.lifecycle.ViewModelAssistedFactory
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Provider


@Module
@InstallIn(ActivityComponent::class)
object ActivityViewModelModule {

    @Provides
    fun provideSavedStateViewModelFactory(
        application: Application,
        activity: Activity,
        viewModelFactories: @JvmSuppressWildcards Map<String, Provider<ViewModelAssistedFactory<out ViewModel>>>
    ): AIGSavedStateViewModelFactory {
        val owner = activity as SavedStateRegistryOwner
        val defaultArgs = activity.intent?.extras
        val delegate = SavedStateViewModelFactory(application, owner, defaultArgs)
        return AIGSavedStateViewModelFactory(owner, defaultArgs, delegate, viewModelFactories)
    }
}
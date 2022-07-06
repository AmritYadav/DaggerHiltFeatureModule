package com.example.daggerhiltdynamicfeature.di

import android.app.Application
import com.example.daggerhiltdynamicfeature.data.TestClass
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface CoreModuleDependencies {

    fun exposeApplication(): Application

    fun provideTestClass(): TestClass
}
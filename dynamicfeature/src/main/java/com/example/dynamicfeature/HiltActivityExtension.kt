package com.example.dynamicfeature

import com.example.daggerhiltdynamicfeature.di.CoreModuleDependencies
import dagger.hilt.android.EntryPointAccessors

fun DynamicFeatureActivity.inject() =
    DaggerDynamicFeatureComponent.factory().dynamicFeatureComponent(
        this,
        EntryPointAccessors.fromApplication(
            applicationContext,
            CoreModuleDependencies::class.java
        )
    ).inject(this)

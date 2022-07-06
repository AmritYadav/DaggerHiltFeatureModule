package com.example.dynamicfeature

import android.app.Activity
import com.example.daggerhiltdynamicfeature.TestViewModel_HiltModule
import com.example.daggerhiltdynamicfeature.di.ActivityViewModelModule
import com.example.daggerhiltdynamicfeature.di.CoreModuleDependencies
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [CoreModuleDependencies::class],
    modules = [ActivityViewModelModule::class, TestViewModel_HiltModule::class]
)
interface DynamicFeatureComponent {

    fun inject(dynamicFeatureActivity: DynamicFeatureActivity)

    @Component.Factory
    interface Factory {
        fun dynamicFeatureComponent(
            @BindsInstance activity: Activity,
            coreModuleDependencies: CoreModuleDependencies
        ): DynamicFeatureComponent
    }

}
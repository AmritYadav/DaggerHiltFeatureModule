package com.example.dynamicfeature

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.daggerhiltdynamicfeature.TestViewModel
import com.example.daggerhiltdynamicfeature.di.AIGSavedStateViewModelFactory
import com.google.android.play.core.splitcompat.SplitCompat
import javax.inject.Inject

class DynamicFeatureActivity : AppCompatActivity() {

    @Inject
    lateinit var savedStateViewModelFactory: AIGSavedStateViewModelFactory

    private val testViewModel: TestViewModel by viewModels { savedStateViewModelFactory }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        SplitCompat.install(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic_feature)

//        testViewModel.printHello()
    }
}
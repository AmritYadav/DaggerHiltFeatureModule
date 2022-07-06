package com.example.daggerhiltdynamicfeature

import android.os.Bundle
import androidx.activity.viewModels
import com.example.daggerhiltdynamicfeature.base.BaseActivity
import com.example.daggerhiltdynamicfeature.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val testViewModel : TestViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.installModule.setOnClickListener {
            installModule(DynamicFeatureModule.DYNAMIC_FEATURE)
        }
    }
}
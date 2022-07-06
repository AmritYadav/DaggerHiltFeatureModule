package com.example.daggerhiltdynamicfeature

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.daggerhiltdynamicfeature.data.TestClass

class TestViewModel @ViewModelInject constructor(private val testClass: TestClass): ViewModel() {

    fun printHello() {
        testClass.printHello()
    }

}
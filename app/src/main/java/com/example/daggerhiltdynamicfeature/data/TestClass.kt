package com.example.daggerhiltdynamicfeature.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestClass @Inject constructor() {

    fun printHello() {
        println("Hello Dynamic World")
    }
}
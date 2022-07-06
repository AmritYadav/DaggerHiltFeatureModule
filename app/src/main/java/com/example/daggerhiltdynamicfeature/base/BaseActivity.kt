package com.example.daggerhiltdynamicfeature.base

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.daggerhiltdynamicfeature.DynamicFeatureModule
import com.google.android.play.core.splitinstall.*
import com.google.android.play.core.splitinstall.model.SplitInstallErrorCode
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity() {

    private var splitInstallManager: SplitInstallManager? = null
    private var listener: SplitInstallStateUpdatedListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splitInstallManager = SplitInstallManagerFactory.create(this)
    }

    override fun onDestroy() {
        if (splitInstallManager != null && listener != null) {
            splitInstallManager?.unregisterListener(listener!!)
        }
        super.onDestroy()
    }

    fun installModule(featureModule: DynamicFeatureModule) {
        checkInstalledModule(featureModule)
    }

    private fun checkInstalledModule(featureModule: DynamicFeatureModule) {
        if (splitInstallManager!!.installedModules.contains(featureModule.moduleName)) {
            onSuccessfulLoad(featureModule)
        } else {
            downloadDynamicModule(featureModule)
        }
    }

    private fun downloadDynamicModule(featureModule: DynamicFeatureModule) {
        val request = SplitInstallRequest
            .newBuilder()
            .addModule(featureModule.moduleName)
            .build()
        listener = SplitInstallStateUpdatedListener { state ->
            when (state.status()) {
                SplitInstallSessionStatus.DOWNLOADING -> {
                }
                SplitInstallSessionStatus.INSTALLED -> {
                    onSuccessfulLoad(featureModule)
                }
                SplitInstallSessionStatus.CANCELED -> {
                    // TODO
                }
                SplitInstallSessionStatus.CANCELING -> {
                    // TODO
                }
                SplitInstallSessionStatus.DOWNLOADED -> {
                    // TODO
                }
                SplitInstallSessionStatus.FAILED -> {
                    // TODO
                }
                SplitInstallSessionStatus.INSTALLING -> {
                    // TODO
                }
                SplitInstallSessionStatus.PENDING -> {
                    // TODO
                }
                SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                    // TODO
                }
                SplitInstallSessionStatus.UNKNOWN -> {
                    // TODO
                }
            }
        }

        splitInstallManager?.registerListener(listener!!)

        splitInstallManager!!
            .startInstall(request)
            .addOnSuccessListener { sessionId ->
                println("sessionId $sessionId")
            }
            .addOnFailureListener { exception ->
                if (exception is SplitInstallException) {
                    if (exception.errorCode == SplitInstallErrorCode.ACTIVE_SESSIONS_LIMIT_EXCEEDED) {
                        checkForActiveDownloads(splitInstallManager!!)
                    }
                }
            }
    }

    private fun checkForActiveDownloads(splitInstallManager: SplitInstallManager) {
        splitInstallManager
            .sessionStates
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (state in task.result) {
                        if (state.status() == SplitInstallSessionStatus.DOWNLOADING) {

                        }
                    }
                }
            }
    }

    private fun onSuccessfulLoad(featureModule: DynamicFeatureModule) {
        for (current in DynamicFeatureModule.values()) {
            if (current == featureModule) {
                launchActivity(featureModule.featureLauncherPath)
                break
            }
        }
    }

    private fun launchActivity(className: String) {
        val intent = Intent().setClassName(packageName, className)
        startActivity(intent)
    }
}
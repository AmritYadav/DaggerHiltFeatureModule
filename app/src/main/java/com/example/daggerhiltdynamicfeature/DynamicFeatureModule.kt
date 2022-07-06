package com.example.daggerhiltdynamicfeature

/**
 * @param featureLauncherPath is the launching path for the corresponding feature
 */
enum class DynamicFeatureModule(val moduleName: String, val featureLauncherPath: String) {
    DYNAMIC_FEATURE("dynamicfeature", "com.example.dynamicfeature.DynamicFeatureActivity");
}
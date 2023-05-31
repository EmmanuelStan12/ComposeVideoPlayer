plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.codedev.base"
    compileSdk = Dependencies.compileSDK

    defaultConfig {
        minSdk = Dependencies.minSDK
        targetSdk = Dependencies.targetSDK

        testInstrumentationRunner = Dependencies.tiRunner

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {

        release {
            isMinifyEnabled = Dependencies.isMinifyEnabled
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }

        debug {
            isMinifyEnabled = false
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.kce_version
    }
}

dependencies {
    api(Dependencies.core)
    api(Dependencies.composeui)
    api(Dependencies.composeactivity)
    api(Dependencies.composeicons)
    api(Dependencies.composejunit)
    api(Dependencies.composepreview)
    api(Dependencies.composetooling)
    api(Dependencies.composenavigation)
    api(Dependencies.composematerial)
    api(Dependencies.constraintlayout)
    api(Dependencies.material)
    api(Dependencies.appcompat)
    api(Dependencies.timber)

    api(project(mapOf("path" to ":libraries:ui-base-lib")))
    api(project(mapOf("path" to ":libraries:room-lib")))
    api(project(mapOf("path" to ":libraries:storage-lib")))
    api(project(mapOf("path" to ":libraries:data-lib")))

    debugApi(Dependencies.customview)
    debugApi(Dependencies.customviewcontainer)

}
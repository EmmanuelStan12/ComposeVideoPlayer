plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.codedev.video"
    compileSdk = Dependencies.compileSDK

    defaultConfig {
        minSdk = Dependencies.minSDK
        targetSdk = Dependencies.targetSDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.kce_version
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(mapOf("path" to ":features:base")))
    implementation(Dependencies.media3_common)
    implementation(Dependencies.media3_ui)
    implementation(Dependencies.media3_player)
    implementation(Dependencies.media3_datasource)
    implementation(Dependencies.media3_session)
    kapt(Dependencies.dagger_kapt)
}
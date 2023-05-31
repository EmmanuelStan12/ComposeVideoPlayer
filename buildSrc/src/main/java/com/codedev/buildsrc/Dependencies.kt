import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {
    val compileSDK = 33
    val minSDK = 23
    val targetSDK = 33
    val versionCode = 1
    val versionName = "1.0.0"

    const val isMinifyEnabled = false
    val isShrinkEnabled = false

    val kotlin_v = "1.7.10"
    val gradle_v = "7.3.1"

    val tiRunner = "androidx.test.runner.AndroidJUnitRunner"

    val kce_version = "1.3.1"

    private val compose_v = "1.3.1"
    val composeui = "androidx.compose.ui:ui:$compose_v"
    val composeactivity = "androidx.activity:activity-compose:$compose_v"
    val composematerial = "androidx.compose.material:material:$compose_v"
    val composepreview = "androidx.compose.ui:ui-tooling-preview:$compose_v"
    val composejunit = "androidx.compose.ui:ui-test-junit4:$compose_v"
    val composetooling = "androidx.compose.ui:ui-tooling:$compose_v"
    val composeicons = "androidx.compose.material:material-icons-extended:$compose_v"

    val customview = "androidx.customview:customview:1.2.0-alpha01"
    val customviewcontainer = "androidx.customview:customview-poolingcontainer:1.0.0-alpha01"

    val composenavigation = "androidx.navigation:navigation-compose:2.5.1"

    private val lifecycle_compose_v = "2.5.1"
    val lifecycleruntime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_compose_v"
    val lifecycleviewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_compose_v"

    val lifecycleruntimecompose = "androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha03"
    val navigationcompose = "androidx.navigation:navigation-compose:2.5.3"
    val activitycompose = "androidx.activity:activity-compose:1.6.1"
    val constraintlayout = "androidx.constraintlayout:constraintlayout-compose:1.0.1"
    val testjunit = "junit:junit:4.13.2"
    val androidtestjunit = "androidx.test.ext:junit:1.1.3"
    val androidespresso = "androidx.test.espresso:espresso-core:3.3.0"
    val accompanistpager = "com.google.accompanist:accompanist-pager:0.25.1"
    val accompanistpagerindicators = "com.google.accompanist:accompanist-pager-indicators:0.25.0"

    val splashscreen = "androidx.core:core-splashscreen:1.0.0"

    private val ktor_v = "2.0.2"
    val ktorclientcore = "io.ktor:ktor-client-core:$ktor_v"
    val ktorclientcio = "io.ktor:ktor-client-cio:$ktor_v"
    val ktorclientcontentnego = "io.ktor:ktor-client-content-negotiation:$ktor_v"
    val ktorclientlogging = "io.ktor:ktor-client-logging:$ktor_v"
    val ktorclientokhttp = "io.ktor:ktor-client-okhttp:$ktor_v"
    val ktorserialization = "io.ktor:ktor-serialization-kotlinx-json:$ktor_v"

    val logbackclassic = "ch.qos.logback:logback-classic:1.2.11"

    val coroutinecore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0"
    //    val coroutineandroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4"
    val coroutinetest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1"

    val coilcompose = "io.coil-kt:coil-compose:2.2.1"

    val accompanistpermissions = "com.google.accompanist:accompanist-permissions:0.28.0"

    val activity_ktx = "androidx.activity:activity-ktx:1.4.0"
    val appcompat = "androidx.appcompat:appcompat:1.5.1"
    val core = "androidx.core:core-ktx:1.9.0"
    val material = "com.google.android.material:material:1.7.0"
    val recyclerview = "androidx.recyclerview:recyclerview:1.3.0"
    val constraint_layout = "androidx.constraintlayout:constraintlayout:2.1.4"
    val cardview = "androidx.cardview:cardview:1.0.0"
    val drawerlayout = "androidx.drawerlayout:drawerlayout:1.1.1"

    val fragmentnavigation = "androidx.navigation:navigation-fragment-ktx:2.5.3"
    val navigationui = "androidx.navigation:navigation-ui-ktx:2.5.3"

    val javax = "javax.inject:javax.inject:1"

    val lifecycle_extensions = "android.arch.lifecycle:extensions:1.1.1"
    val dotsindicator = "com.tbuonomo:dotsindicator:4.3"

    private val lifecycle_v = "2.4.0"
    val lifecycle_viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_v"
    val lifecycle_livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_v"
    val lifecycle_runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_v"
    val lifecycle_reactive_streams = "androidx.lifecycle:lifecycle-reactivestreams-ktx:$lifecycle_v"

    val work_runtime = "androidx.work:work-runtime-ktx:2.7.1"

    private val room_v = "2.5.0"
    val room = "androidx.room:room-ktx:$room_v"
    val room_runtime = "androidx.room:room-runtime:$room_v"
    val room_rxjava = "androidx.room:room-rxjava3:$room_v"
    val room_kapt = "androidx.room:room-compiler:$room_v"
    val room_testing = "androidx.room:room-testing:$room_v"

    val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4"
    val coroutines_play_services = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.1.1"

    val glide = "com.github.bumptech.glide:glide:4.13.0"

    val circleiv = "de.hdodenhof:circleimageview:3.1.0"


    private val dagger_v = "2.42"
    val dagger = "com.google.dagger:dagger:$dagger_v"
    val dagger_kapt = "com.google.dagger:dagger-compiler:$dagger_v"

    private val retrofit_v = "2.9.0"
    val retrofit = "com.squareup.retrofit2:retrofit:$retrofit_v"
    val retrofit_gson = "com.squareup.retrofit2:converter-gson:$retrofit_v"

    val logging_interceptor = "com.squareup.okhttp3:logging-interceptor:4.9.1"

    val gson = "com.google.code.gson:gson:2.9.1"

    val timber = "com.jakewharton.timber:timber:5.0.1"

    val fragment = "androidx.fragment:fragment:1.5.5"
    val fragment_testing = "androidx.fragment:fragment-testing:1.5.5"

    val media3_version = "1.0.1"

    // For media playback using ExoPlayer
    val media3_player = "androidx.media3:media3-exoplayer:$media3_version"

    // For DASH playback support with ExoPlayer
    val media3_dash = "androidx.media3:media3-exoplayer-dash:$media3_version"
    // For HLS playback support with ExoPlayer
    val media3_hls = "androidx.media3:media3-exoplayer-hls:$media3_version"
    // For RTSP playback support with ExoPlayer
    val media3_rtsp = "androidx.media3:media3-exoplayer-rtsp:$media3_version"
    // For ad insertion using the Interactive Media Ads SDK with ExoPlayer
    val media3_ad = "androidx.media3:media3-exoplayer-ima:$media3_version"

    // For loading data using the Cronet network stack
    val media3_cronet = "androidx.media3:media3-datasource-cronet:$media3_version"
    // For loading data using the OkHttp network stack
    val media3_okhttp = "androidx.media3:media3-datasource-okhttp:$media3_version"
    // For loading data using librtmp
    val media3_librtmp = "androidx.media3:media3-datasource-rtmp:$media3_version"

    // For building media playback UIs
    val media3_ui = "androidx.media3:media3-ui:$media3_version"
    // For building media playback UIs for Android TV using the Jetpack Leanback library
    val media3_tv_ui = "androidx.media3:media3-ui-leanback:$media3_version"

    // For exposing and controlling media sessions
    val media3_session = "androidx.media3:media3-session:$media3_version"

    // For extracting data from media containers
    val media3_extractor = "androidx.media3:media3-extractor:$media3_version"

    // For integrating with Cast
    val media3_cast = "androidx.media3:media3-cast:$media3_version"

    // For scheduling background operations using Jetpack Work's WorkManager with ExoPlayer
    val media3_workmanager = "androidx.media3:media3-exoplayer-workmanager:$media3_version"

    // For transforming media files
    val media3_transform = "androidx.media3:media3-transformer:$media3_version"

    // Utilities for testing media components (including ExoPlayer components)
    val media3_test_utils = "androidx.media3:media3-test-utils:$media3_version"
    // Utilities for testing media components (including ExoPlayer components) via Robolectric
    val media3_roboelectric = "androidx.media3:media3-test-utils-robolectric:$media3_version"

    // Common functionality for media database components
    val media3_database = "androidx.media3:media3-database:$media3_version"
    // Common functionality for media decoders
    val media3_decoder = "androidx.media3:media3-decoder:$media3_version"
    // Common functionality for loading data
    val media3_datasource = "androidx.media3:media3-datasource:$media3_version"
    // Common functionality used across multiple media libraries
    val media3_common = "androidx.media3:media3-common:$media3_version"

    val appLibraries: ArrayList<String> = arrayListOf<String>().apply {
        add(core)
        add(appcompat)
        add(material)
    }

    fun DependencyHandler.implementation(list: List<String>){
        list.forEach { dependency -> add("implementation", dependency) }
    }

    fun DependencyHandler.implementation(vararg depends:String){
        depends.forEach { dependency -> add("implementation", dependency) }
    }

    fun DependencyHandler.debugImplementation(vararg depends:String){
        depends.forEach { dependency -> add("debugImplementation", dependency) }
    }

//    fun DependencyHandler.debugApi(vararg depends:String){
//        depends.forEach { dependency -> add("debugApi", dependency) }
//    }

    fun DependencyHandler.addTestDependencies(){
        add("testImplementation", testjunit)
    }

    fun DependencyHandler.addAndroidTestDependencies(){
        add("androidTestImplementation", androidtestjunit)
        add("androidTestImplementation", androidespresso)
    }
}
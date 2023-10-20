import java.util.Properties

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    // alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.kotlinParcelize)
    alias(libs.plugins.daggerHilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinSerialization)
}

val properties = Properties()
project.rootProject.file("local.properties").inputStream().use { properties.load(it) }

android {
    namespace = "com.tmdb.movie"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tmdb.movie"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        resValue("string", "tmdb_access_token", properties.getProperty("TMDB_ACCESS_TOKEN"))
    }

    signingConfigs {
        create("release") {
            storeFile = file(properties.getProperty("storeFile"))
            storePassword = properties.getProperty("storePassword")
            keyAlias = properties.getProperty("keyAlias")
            keyPassword = properties.getProperty("keyPassword")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isDebuggable = true
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

configurations.all {
    resolutionStrategy {
//         force("androidx.compose.foundation:foundation:1.6.0-alpha07")
//         force("androidx.compose.foundation:foundation-layout:1.6.0-alpha07")
    }
}

dependencies {
    implementation(project(":lib-renderscript"))

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.navigation.compose)
    // Hilt
    implementation(libs.dagger.hilt)
    ksp(libs.dagger.hilt.compiler)
    // Hilt Navigation Compose
    implementation(libs.hilt.navigation.compose)
    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.loggin.interceptor)
    // Paging
    implementation(libs.paging.runtime)
    implementation(libs.paging.compose)
    // Coil
    implementation(libs.coil.compose)
    implementation(libs.coil.svg)
    // Custom Tab Browser
    implementation(libs.custom.tab.browser)
    // DataStore
    implementation(libs.datastore.preferences)
    // Persistent CookieJar
    implementation(libs.persistent.cookieJar)
    // Accompanist Placeholder Material
    implementation(libs.accompanist.placeholder)
    // Compose Material
    implementation(libs.compose.material)
    // Lifecycle Runtime Compose
    implementation(libs.lifecycle.runtime.compose)
    // Compose Foundation
    implementation(libs.compose.foundation)
    implementation(libs.compose.foundation.layout)
    // Kotlin Serialization
    implementation(libs.kotlinx.serialization.json)
    // DataStore
    implementation(libs.datastore)
    // SplashScreen
    implementation(libs.splash.screen)
    // Appcompat
    implementation(libs.appcompat)
    // Accompanist Systemuicontroller
    implementation(libs.accompanist.systemuicontroller)
    // Rating Bar
    implementation(libs.compose.ratingbar)
    // Runtime Tracing
    implementation(libs.runtime.tracing)
    // Text Flow
    implementation(libs.text.flow)
    // Read More Text
    implementation(libs.readmore.text)
    // Compose ui-util
    implementation(libs.ui.util)
    // Cloudy
    // implementation(libs.cloudy)
    // Landscapist Coil
    implementation(libs.landscapist.coil)
    // Landscapist Transformation
    // implementation(libs.landscapist.transformation)
    // Room Database
    implementation(libs.room)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
    // Glide
    implementation(libs.glide)
    ksp(libs.glide.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}
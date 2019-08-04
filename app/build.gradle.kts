plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(AndroidSdkVersions.ANDROID_COMPILE_SDK_VERSION)
    defaultConfig {
        applicationId = "com.demo.justapp.exchanger"
        targetSdkVersion(AndroidSdkVersions.ANDROID_TARGET_SDK_VERSION)
        minSdkVersion(AndroidSdkVersions.ANDROID_MIN_SDK_VERSION)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("debug") {
            isDebuggable = true
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    packagingOptions.exclude("META-INF/main.kotlin_module")

    compileOptions {
        sourceCompatibility = AndroidSdkVersions.SOURCE_COMPATIBILITY_VERSION
        targetCompatibility = AndroidSdkVersions.TARGET_COMPATIBILITY_VERSION
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
    }
}

dependencies {
    //android-support
    implementation(AndroidSupportLibraryDependencies.appcompat)
    implementation(AndroidSupportLibraryDependencies.design)
    implementation(AndroidSupportLibraryDependencies.supportV4)
    implementation(AndroidSupportLibraryDependencies.recyclerview)

    //dagger2
    implementation(LibsDependencies.daggerRuntime)
    kapt(LibsDependencies.daggerCompiler)

    //rxjava2
    implementation(LibsDependencies.rxjava)
    implementation(LibsDependencies.rxandroid)

    //network
    implementation(LibsDependencies.okhttp)
    implementation(LibsDependencies.okhttpLogging)
    implementation(LibsDependencies.retrofit)
    implementation(LibsDependencies.retrofitAdapterRx)
    implementation(LibsDependencies.retrofitConverterGson)

    //moxy
    implementation(LibsDependencies.moxy)
    implementation(LibsDependencies.moxyAndroid)
    kapt(LibsDependencies.moxyCompiler)

    //other
    implementation(LibsDependencies.gson)
    implementation(LibsDependencies.timber)
    implementation(LibsDependencies.constraintlayout)
    implementation(LibsDependencies.kotlin)

    //testing
    testImplementation(TestingLibraryDependencies.junit)
    testImplementation(TestingLibraryDependencies.mockitokotlin)
    androidTestImplementation(TestingLibraryDependencies.runner)
    androidTestImplementation(TestingLibraryDependencies.espressoCore)
}

androidExtensions {
    isExperimental = true
}

repositories {
    jcenter()
    google()
}

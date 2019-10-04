object AndroidSupportLibraryDependencies {
    const val appcompat = "androidx.appcompat:appcompat:${AndroidSupportVersions.APP_COMPACT_LIBRARY_VERSION}"
    const val design = "com.google.android.material:material:${AndroidSupportVersions.ANDROID_COMPACT_LIBRARY_VERSION}"
    const val supportV4 = "androidx.legacy:legacy-support-v4:${AndroidSupportVersions.ANDROID_COMPACT_LIBRARY_VERSION}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${AndroidSupportVersions.ANDROID_COMPACT_LIBRARY_VERSION}"
}

object Plugins {
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${LibsVersions.KOTLIN_LIBRARY_VERSION}"
    const val gradle = "com.android.tools.build:gradle:${AndroidSdkVersions.GRADLE_PLUGIN_VERSION}"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val kotlinKapt = "kotlin-kapt"
}

object LibsDependencies {
    const val daggerRuntime = "com.google.dagger:dagger:${LibsVersions.DAGGER_LIBRARY_VERSION}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${LibsVersions.DAGGER_LIBRARY_VERSION}"

    const val rxjava = "io.reactivex.rxjava2:rxjava:${LibsVersions.RX_JAVA_LIBRARY_VERSION}"
    const val rxandroid = "io.reactivex.rxjava2:rxandroid:${LibsVersions.RX_ANDROID_LIBRARY_VERSION}"

    const val okhttp = "com.squareup.okhttp3:okhttp:${LibsVersions.OK_HTTP_LIBRARY_VERSION}"
    const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${LibsVersions.OK_HTTP_LIBRARY_VERSION}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${LibsVersions.RETROFIT_LIBRARY_VERSION}"
    const val retrofitAdapterRx = "com.squareup.retrofit2:adapter-rxjava2:${LibsVersions.RETROFIT_LIBRARY_VERSION}"
    const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${LibsVersions.RETROFIT_LIBRARY_VERSION}"

    const val moxy = "com.github.moxy-community:moxy:${LibsVersions.MOXY_LIBRARY_VERSION}"
    const val moxyAndroid = "com.github.moxy-community:moxy-androidx:${LibsVersions.MOXY_LIBRARY_VERSION}"
    const val moxyCompiler = "com.github.moxy-community:moxy-compiler:${LibsVersions.MOXY_LIBRARY_VERSION}"

    const val gson = "com.google.code.gson:gson:${LibsVersions.GSON_LIBRARY_VERSION}"
    const val timber = "com.jakewharton.timber:timber:${LibsVersions.TIMBER_LIBRARY_VERSION}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${LibsVersions.CONSTRAINT__LAYOUT_LIBRARY_VERSION}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${LibsVersions.KOTLIN_LIBRARY_VERSION}"
}

object TestingLibraryDependencies {
    const val junit = "org.junit.jupiter:junit-jupiter:${TestingLibsVersions.JUNIT_LIBRARY_VERSION}"
    const val mockk = "io.mockk:mockk:${TestingLibsVersions.MOCKK_LIBRARY_VERSION}"
    const val runner = "androidx.test:runner:${TestingLibsVersions.RUNNER_LIBRARY_VERSION}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${TestingLibsVersions.ESPRESSO_CORE_LIBRARY_VERSION}"
}
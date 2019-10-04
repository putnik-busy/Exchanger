import org.gradle.api.JavaVersion

object AndroidSdkVersions {
    const val ANDROID_COMPILE_SDK_VERSION = 29
    const val ANDROID_MIN_SDK_VERSION = 16
    const val ANDROID_TARGET_SDK_VERSION = 29
    const val GRADLE_PLUGIN_VERSION = "3.5.0"
    val SOURCE_COMPATIBILITY_VERSION = JavaVersion.VERSION_1_8
    val TARGET_COMPATIBILITY_VERSION = JavaVersion.VERSION_1_8
}

object AndroidSupportVersions {
    const val APP_COMPACT_LIBRARY_VERSION = "1.0.2"
    const val ANDROID_COMPACT_LIBRARY_VERSION = "1.0.0"
}

object LibsVersions {
    const val DAGGER_LIBRARY_VERSION = "2.24"
    const val RX_JAVA_LIBRARY_VERSION = "2.2.11"
    const val RX_ANDROID_LIBRARY_VERSION = "2.1.1"
    const val OK_HTTP_LIBRARY_VERSION = "4.0.1"
    const val RETROFIT_LIBRARY_VERSION = "2.6.1"
    const val MOXY_LIBRARY_VERSION = "1.0.13"
    const val GSON_LIBRARY_VERSION = "2.8.5"
    const val TIMBER_LIBRARY_VERSION = "4.7.1"
    const val CONSTRAINT__LAYOUT_LIBRARY_VERSION = "1.1.3"
    const val KOTLIN_LIBRARY_VERSION = "1.3.50"
}

object TestingLibsVersions {
    const val JUNIT_LIBRARY_VERSION = "5.5.2"
    const val MOCKK_LIBRARY_VERSION = "1.9.3"
    const val RUNNER_LIBRARY_VERSION = "1.0.1"
    const val ESPRESSO_CORE_LIBRARY_VERSION = "3.1.0"
}
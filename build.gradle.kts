import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.dsl.Lint
import dev.detekt.gradle.extensions.DetektExtension
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

private val detektPluginId = "dev.detekt"
private val androidApplicationPluginId = "com.android.application"
private val androidLibraryPluginId = "com.android.library"

private val targetSdkVersion = 37
private val compileSdkVersion = targetSdkVersion
private val minSdkVersion = 28

private val javaVersion = JavaVersion.VERSION_21

private val applicationVersionCode = 1
private val applicationVersionName = "1.0.0-alpha"

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.detekt) apply false
}

subprojects {
    plugins.apply(detektPluginId)

    plugins.withId(androidApplicationPluginId) {
        extensions.configure<ApplicationExtension> {
            lint.configureAndroidLint()
            configureAndroidApplication()
        }

        extensions.configure<DetektExtension> {
            configureStaticAnalysis()
        }
    }

    plugins.withId(androidLibraryPluginId) {
        extensions.configure<LibraryExtension> {
            lint.configureAndroidLint()
            configureAndroidLibrary()
        }

        extensions.configure<DetektExtension> {
            configureStaticAnalysis()
        }
    }

    tasks.withType<KotlinCompile> {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_21
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

fun Lint.configureAndroidLint() {
    abortOnError = true
    checkAllWarnings = true
    warningsAsErrors = false
}

fun DetektExtension.configureStaticAnalysis() {
    config.setFrom(file("config/detekt/detekt.yml"))
    buildUponDefaultConfig = true
}

fun LibraryExtension.configureAndroidLibrary() {
    compileSdk {
        version = release(compileSdkVersion)
    }

    defaultConfig {
        minSdk = minSdkVersion
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }
}

fun ApplicationExtension.configureAndroidApplication() {
    compileSdk {
        version = release(compileSdkVersion)
    }

    defaultConfig {
        applicationId = "com.kaylentravispillay.tracker"
        minSdk = minSdkVersion
        targetSdk = targetSdkVersion

        versionCode = applicationVersionCode
        versionName = applicationVersionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }
}

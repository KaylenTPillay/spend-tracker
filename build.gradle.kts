import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.dsl.Lint
import dev.detekt.gradle.extensions.DetektExtension

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.detekt) apply false
}

subprojects {
    plugins.apply("dev.detekt")
    plugins.withId("com.android.application") {
        extensions.configure<ApplicationExtension> {
            configureAndroidLint(lint)
        }
        extensions.configure<DetektExtension> {
            configureStaticAnalysis()
        }
    }
    plugins.withId("com.android.library") {
        extensions.configure<LibraryExtension> {
            configureAndroidLint(lint)
        }
        extensions.configure<DetektExtension> {
            configureStaticAnalysis()
        }
    }
}

fun configureAndroidLint(lint: Lint) {
    lint.apply {
        abortOnError = true
        checkAllWarnings = true
        warningsAsErrors = false
    }
}

fun DetektExtension.configureStaticAnalysis() {
    config.setFrom(file("config/detekt/detekt.yml"))
    buildUponDefaultConfig = true
}

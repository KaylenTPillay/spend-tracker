import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.dsl.Lint

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.android.library) apply false
}

subprojects {
    plugins.withId("com.android.application") {
        extensions.configure<ApplicationExtension> {
            configureAndroidLint(lint)
        }
    }
    plugins.withId("com.android.library") {
        extensions.configure<LibraryExtension> {
            configureAndroidLint(lint)
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

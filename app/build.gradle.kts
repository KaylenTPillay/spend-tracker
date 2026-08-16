plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.kaylentravispillay.tracker"
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":feature:dashboard"))
    implementation(platform(libs.junit.bom))
    implementation(platform(libs.compose.bom))

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.activity.compose)
    implementation(libs.material)
    implementation(libs.hilt.android)
    implementation(libs.coroutines.android)
    implementation(libs.compose.material3)
    implementation(libs.compose.preview)

    debugImplementation(libs.compose.tooling)

    ksp(libs.hilt.compiler)

    testImplementation(libs.junit.jupiter)
    testImplementation(libs.kotest.assertions.core)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.cash.turbine)
    testRuntimeOnly(libs.junit.platform)

    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.mockk.android)
}
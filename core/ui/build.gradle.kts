plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.kaylentravispillay.core.ui"
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(platform(libs.junit.bom))

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.runtime)
    implementation(libs.material)
    implementation(libs.compose.material3)
    implementation(libs.compose.preview)
    implementation(libs.compose.material.icons)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.coroutines.android)

    debugImplementation(libs.compose.tooling)

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
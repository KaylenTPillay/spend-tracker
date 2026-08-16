plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.kaylentravispillay.feature.categories"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:ui"))
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
    implementation(libs.hilt.android)

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
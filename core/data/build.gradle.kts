plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.kaylentravispillay.core.data"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:domain"))
    implementation(platform(libs.junit.bom))

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)

    implementation(libs.androidx.room3.runtime)

    implementation(libs.hilt.android)

    ksp(libs.androidx.room3.compiler)
    ksp(libs.hilt.compiler)

    testImplementation(libs.junit.jupiter)
    testImplementation(libs.kotest.assertions.core)
    testRuntimeOnly(libs.junit.platform)

    androidTestImplementation(libs.androidx.junit)
}
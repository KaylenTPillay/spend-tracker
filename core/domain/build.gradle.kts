plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.kaylentravispillay.core.domain"
}

dependencies {
    implementation(platform(libs.junit.bom))

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)

    testImplementation(libs.junit.jupiter)
    testImplementation(libs.kotest.assertions.core)
    testRuntimeOnly(libs.junit.platform)

    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}
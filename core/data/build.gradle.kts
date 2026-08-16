plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.room)
}

android {
    namespace = "com.kaylentravispillay.core.data"
}

room3 {
    schemaDirectory("$projectDir/schema")
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:domain"))
    implementation(platform(libs.junit.bom))

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.coroutines.android)
    implementation(libs.androidx.room3.runtime)
    implementation(libs.hilt.android)

    ksp(libs.androidx.room3.compiler)
    ksp(libs.hilt.compiler)

    testImplementation(libs.junit.jupiter)
    testImplementation(libs.mockk)
    testRuntimeOnly(libs.junit.platform)

    @Suppress("AvoidDuplicateDependencies")
    testImplementation(libs.coroutines.test)
    @Suppress("AvoidDuplicateDependencies")
    testImplementation(libs.cash.turbine)
    @Suppress("AvoidDuplicateDependencies")
    testImplementation(libs.kotest.assertions.core)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.mockk.android)
    androidTestImplementation(libs.androidx.test.runner)

    @Suppress("AvoidDuplicateDependencies")
    androidTestImplementation(libs.coroutines.test)
    @Suppress("AvoidDuplicateDependencies")
    androidTestImplementation(libs.cash.turbine)
    @Suppress("AvoidDuplicateDependencies")
    androidTestImplementation(libs.kotest.assertions.core)

    androidTestUtil(libs.androidx.test.orchestrator)
}
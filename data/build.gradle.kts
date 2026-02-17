plugins {
    alias(libs.plugins.android.library)
    id("kotlin-parcelize")
    alias(libs.plugins.ksp)
    id("moremovies.spotless-convention")
}

android {
    namespace = "com.hanitacm.moremovies.data"
    compileSdk = libs.versions.compileSdk.get().toInt()

    buildFeatures {
        buildConfig = true
    }
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.javaVersion.get())
        targetCompatibility = JavaVersion.toVersion(libs.versions.javaVersion.get())
    }
    kotlin {
        jvmToolchain {
            languageVersion.set(JavaLanguageVersion.of(libs.versions.jvmTarget.get()))
        }
    }
}

dependencies {

    implementation(libs.coroutines.core)
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
    implementation(libs.moshi)
    ksp(libs.moshi.codegen)
    implementation(libs.room)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi)

    testImplementation(libs.coroutines.test)
    testImplementation(libs.junit)
    testImplementation(libs.mockito)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.room.testing)
}

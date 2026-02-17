plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
    id("kotlin-parcelize")
    alias(libs.plugins.kotlin.compose)
    id("moremovies.spotless-convention")
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.hanitacm.moremovies"
        minSdk = libs.versions.minSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.hanitacm.moremovies.CustomTestRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.javaVersion.get())
        targetCompatibility = JavaVersion.toVersion(libs.versions.javaVersion.get())
    }
    kotlin {
        jvmToolchain(libs.versions.jvmTarget.get().toInt())
    }

    namespace = "com.hanitacm.moremovies"
}

dependencies {
    val composeBom = platform(libs.compose)

    implementation(project(":data"))

    implementation(libs.androidx.ktx)
    implementation(libs.androidx.lifecycle)
    implementation(libs.coroutines.android)
    implementation(libs.coroutines.core)
    implementation(libs.hilt)
    implementation(libs.hilt.compose)
    ksp(libs.hilt.compiler)

    implementation(libs.coil.compose)
    implementation(libs.navigation.compose)
    implementation(libs.navigation.ui)
    implementation(composeBom)
    implementation(libs.compose.material)
    implementation(libs.compose.tooling.preview)
    debugImplementation(libs.compose.tooling)

    testImplementation(libs.coroutines.test)
    testImplementation(libs.junit)
    testImplementation(libs.mockito)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.turbine)

    androidTestImplementation(composeBom)
    androidTestImplementation(libs.compose.ui.test)
    debugImplementation(libs.compose.ui.testing.rules)
    androidTestImplementation(libs.navigation.test)
    androidTestImplementation(libs.espresso)
    androidTestImplementation(libs.hilt.test)
    kspAndroidTest(libs.hilt.compiler)
    androidTestImplementation(libs.junit.test)
}

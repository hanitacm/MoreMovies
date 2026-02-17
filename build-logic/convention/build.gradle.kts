plugins {
    `kotlin-dsl`
}

group = "com.hanitacm.moremovies.buildlogic"

java {
    sourceCompatibility = JavaVersion.toVersion(libs.versions.javaVersion.get())
    targetCompatibility = JavaVersion.toVersion(libs.versions.javaVersion.get())
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.spotless.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("spotlessConvention") {
            id = "moremovies.spotless-convention"
            implementationClass = "com.hanitacm.moremovies.buildlogic.SpotlessConventionPlugin"
        }
    }
}

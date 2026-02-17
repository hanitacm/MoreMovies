package com.hanitacm.moremovies.buildlogic

import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class SpotlessConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.diffplug.spotless")

            extensions.configure<SpotlessExtension> {
                // Configure Spotless formatting for Kotlin files
                kotlin {
                    target("**/*.kt")
                    targetExclude("**/build/**/*.kt") // Exclude build directories
                    ktlint()
                        .setEditorConfigPath("$rootDir/.editorconfig")
                    trimTrailingWhitespace()
                    endWithNewline()
                }
                // Configure formatting for Gradle files
                kotlinGradle {
                    target("*.gradle.kts")
                    ktlint()
                }
            }
        }
    }
}

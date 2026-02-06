@file:OptIn(ExperimentalDistributionDsl::class)

import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalDistributionDsl

plugins {
    alias(libs.plugins.jetbrains.kotlin.multiplatform)
}

group = rootProject.group
version = rootProject.version


kotlin {
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    )

    jvm()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            distribution {
                outputDirectory.set(layout.buildDirectory.dir("wasm"))
            }
        }
        binaries.executable()
    }

    explicitApi()

    sourceSets {

        commonMain.dependencies {
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test.common)
        }
    }
}
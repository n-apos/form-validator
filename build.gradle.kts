plugins {
    alias(libs.plugins.jetbrains.kotlin.multiplatform) apply false
}

group = "com.napos.form"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
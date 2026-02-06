plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
rootProject.name = "form-validator"

include(
    "core",
    "example",
)

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
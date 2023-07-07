plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization").version("1.8.22")
    id("com.google.devtools.ksp").version("1.8.21-1.0.11")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    val coroutinesVersion = "1.6.4"
    val ktorVersion = "2.2.4"
    val sqlDelightVersion = "1.5.5"
    val dateTimeVersion = "0.4.0"
    val koinVersion = "3.3.0"

    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:$dateTimeVersion")

                //ktor dependencies for serialization/deserialization
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

                //coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

                // koin
                implementation("io.insert-koin:koin-core:$koinVersion")

                //SQLDelight
                implementation("com.squareup.sqldelight:runtime:$sqlDelightVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:$ktorVersion")
                implementation("androidx.lifecycle:lifecycle-livedata-core-ktx:2.6.1")
                implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
                implementation("io.insert-koin:koin-androidx-compose:3.3.0")
            }
        }

        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
             }
        }
    }
}

android {
    namespace = "com.example.rickandmorty"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}

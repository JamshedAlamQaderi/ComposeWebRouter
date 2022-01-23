import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform") version "1.5.31"
    id("org.jetbrains.compose") version "1.0.0"
    kotlin("plugin.serialization") version "1.6.10"
    id("maven-publish")
}

group = "com.jaq.kotlin"
version = "0.0.3"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    js(IR) {
        browser {
            testTask {
                useKarma {
//                    useChrome()
                }
            }
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(compose.web.core)
                implementation(compose.runtime)
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
            }

        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}

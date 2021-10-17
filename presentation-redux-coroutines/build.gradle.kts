plugins {
    kotlin("multiplatform")
    id("maven-publish")
}

group = Artifacts.Presentation.reduxCoroutines.group
version = Artifacts.Presentation.reduxCoroutines.version

repositories {
    mavenCentral()
    mavenLocal()
}
kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
    }

    ios()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Artifacts.Presentation.redux.libraryName)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
        val iosMain by getting
        val iosTest by getting
    }
}

publishing {
    repositories {
        maven {
            name = "GitHub"
            url = uri("https://maven.pkg.github.com/eadm/AndroidKit")
            credentials {
                username = System.getenv("GITHUB_USER")
                    ?: project.properties["GITHUB_USER"] as String?

                password = System.getenv("GITHUB_PERSONAL_ACCESS_TOKEN")
                    ?: project.properties["GITHUB_PERSONAL_ACCESS_TOKEN"] as String?
            }
        }
    }
}
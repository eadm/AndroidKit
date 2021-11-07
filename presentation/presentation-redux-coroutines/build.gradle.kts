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
                implementation(rootProject.extra.library("coroutinesMt"))
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
        github(project, REPOSITORY_URL) {
            name = "GitHub"
        }
    }
}
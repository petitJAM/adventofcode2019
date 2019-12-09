plugins {
    kotlin("jvm") version "1.3.61"
}

group = "com.alexpetitjean"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

tasks {

    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
        kotlinOptions.freeCompilerArgs = listOf("-Xinline-classes")
    }

    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

plugins {
    kotlin("jvm") version "1.3.61"
}

group = "com.alexpetitjean"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://dl.bintray.com/spekframework/spek-dev/")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    testRuntimeOnly(kotlin("reflect"))
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))

    // Spek

    val spekVersion = "2.0.9"
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spekVersion") {
        exclude(group = "org.jetbrains.kotlin")
    }
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:$spekVersion") {
        exclude(group = "org.junit.platform")
        exclude(group = "org.jetbrains.kotlin")
    }
    testImplementation(group = "org.junit.platform", name = "junit-platform-engine", version = "1.3.0-RC1")
}

tasks {

    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
        kotlinOptions.freeCompilerArgs = listOf("-Xinline-classes")
    }

    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

    withType<Test> {
        useJUnitPlatform {
            includeEngines("spek2")
        }
    }
}

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.21"
    kotlin("kapt") version "1.5.21"
    application
}

group = "land.generic"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation("dev.kord:kord-core:0.8.0-M4")
    implementation("com.google.dagger:dagger:2.38.1")
    kapt("com.google.dagger:dagger-compiler:2.38.1")
    implementation(group = "com.uchuhimo", name = "konf", version = "1.1.2")
    implementation("com.soywiz.korlibs.korim:korim-jvm:2.3.1")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnit()
}
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "MainKt"
}
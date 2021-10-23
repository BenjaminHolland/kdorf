import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    kotlin("jvm") version "1.5.21"
    kotlin("kapt") version "1.5.21"
    kotlin("plugin.serialization") version "1.5.21"
    application
}

group = "land.generic"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
}
fun DependencyHandlerScope.dagger(){
    val version = "2.39.1"
    implementation("com.google.dagger:dagger:$version")
    kapt("com.google.dagger:dagger-compiler:$version")
}
fun DependencyHandlerScope.logging(){
    val version = "2.14.1"
    implementation("org.apache.logging.log4j:log4j-api:$version")
    implementation("org.apache.logging.log4j:log4j-core:$version")
    implementation("org.slf4j:slf4j-simple:1.7.32")
}
dependencies {
    implementation("dev.kord:kord-core:0.8.0-M4")
    dagger()
    logging()
    implementation(group = "com.uchuhimo", name = "konf", version = "1.1.2")
    implementation("com.soywiz.korlibs.korim:korim-jvm:2.4.6")
    implementation("com.google.cloud:google-cloud-firestore:3.0.5")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnit()
}
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}
tasks.jar{
    manifest {
        attributes(mapOf("Implementation-Title" to project.name,
            "Implementation-Version" to project.version,
            "Main-Class" to "MainKt"))
    }
}
application {
    mainClassName = "MainKt"
}
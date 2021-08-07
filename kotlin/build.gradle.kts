import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.5.20"
    application
}

group = "xyz.angm"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "xyz.angm.collatz.CollatzKt"
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "xyz.angm.collatz.CollatzKt"
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from({ configurations.compileClasspath.get().resolve().map { if (it.isDirectory) it else zipTree(it) } })
}

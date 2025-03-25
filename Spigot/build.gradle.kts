plugins {
    id("java")
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.14" apply false
    id("io.github.goooler.shadow") version "8.1.8" apply false
}

group = "net.rankedproject"
version = "1.0-SNAPSHOT"

subprojects {
    plugins.apply("io.github.goooler.shadow")

    repositories {
        maven("https://repo.papermc.io/repository/maven-public/")
        gradlePluginPortal()

        mavenLocal()
        mavenCentral()
    }

    dependencies {
        compileOnly("org.projectlombok:lombok:1.18.30")
        implementation("com.github.ben-manes.caffeine:caffeine:3.2.0")
        annotationProcessor("org.projectlombok:lombok:1.18.30")

        api(project(":Common"))
    }
}
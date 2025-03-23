plugins {
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.14"
}

group = "net.rankedproject"
version = "1.0-SNAPSHOT"

repositories {
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }

    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.dmulloy2.net/repository/public/")

    mavenLocal()
    mavenCentral()
}

dependencies {
    paperweight.paperDevBundle("1.21.4-R0.1-SNAPSHOT")
    api(project(":Common"))
    compileOnly("org.projectlombok:lombok:1.18.30")
    implementation("com.github.ben-manes.caffeine:caffeine:3.2.0")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

description = "CommonSpigot"
plugins {
    id("java")
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.14" apply false
    id("com.gradleup.shadow") version "9.2.2" apply false
}

group = "net.rankedproject"
version = "1.0-SNAPSHOT"

subprojects {
    plugins.apply("com.gradleup.shadow")

    repositories {
        maven("https://repo.papermc.io/repository/maven-public/")
        maven("https://repo.infernalsuite.com/repository/maven-snapshots/")
        maven("https://repo.infernalsuite.com/repository/maven-releases/")
        gradlePluginPortal()

        mavenLocal()
        mavenCentral()
    }

    dependencies {
        api(project(":Common"))
        api("com.infernalsuite.asp:mysql-loader:4.0.0-SNAPSHOT")
        api("org.incendo:cloud-paper:2.0.0-beta.10")
        api("org.reflections:reflections:0.10.2")
        compileOnly("org.projectlombok:lombok:1.18.42")
        compileOnly("com.infernalsuite.asp:api:4.0.0-SNAPSHOT")
        annotationProcessor("org.projectlombok:lombok:1.18.42")
    }
}
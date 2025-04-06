group = "net.rankedproject"
version = "1.0-SNAPSHOT"

plugins {
    id("io.papermc.paperweight.userdev")
}

dependencies {
    paperweight.paperDevBundle("1.21.4-R0.1-SNAPSHOT")
    implementation("org.incendo:cloud-paper:2.0.0-beta.10")
}

subprojects {
    apply(plugin = "io.papermc.paperweight.userdev")
    apply(plugin = "io.github.goooler.shadow:8.1.8")
    dependencies {
        paperweight.paperDevBundle("1.21.4-R0.1-SNAPSHOT")
    }
}

description = "CommonSpigot"
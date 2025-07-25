group = "net.rankedproject"
version = "1.0-SNAPSHOT"

plugins {
    id("io.papermc.paperweight.userdev")
}

dependencies {
    paperweight.paperDevBundle("1.21.4-R0.1-SNAPSHOT")
    api(project(":Spigot:CommonSpigot"))
}
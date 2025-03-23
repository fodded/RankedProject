allprojects {
    apply(plugin = "java")
    apply(plugin = "java-library")
    apply(plugin = "idea")

    repositories {
        maven("https://repo.codemc.io/repository/maven-snapshots/")
        maven("https://repo.codemc.io/repository/maven-releases/")
        maven("https://oss.sonatype.org/content/repositories/central")
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://repo.glaremasters.me/repository/concuncan/")

        mavenCentral()
    }
}
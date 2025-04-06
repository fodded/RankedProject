group = "net.rankedproject"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.30")
    implementation("com.google.code.gson:gson:2.12.1")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.github.ben-manes.caffeine:caffeine:3.2.0")
    implementation("it.unimi.dsi:fastutil:8.2.1")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
}

tasks.test {
    useJUnitPlatform()
}
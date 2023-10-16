plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("maven-publish")
}

group = "de.tomjuri"
version = "3.3.0"

val lwjgl: Configuration by configurations.creating

repositories {
    mavenCentral()
}

tasks.shadowJar {
    archiveClassifier.set("")
    configurations = listOf(lwjgl)
    excludes.addAll(listOf(
        "META-INF/versions/**",
        "**/module-info.class",
        "**/package-info.class"
    ))
    relocate("org.lwjgl", "org.lwjgl3") {
        include("org.lwjgl.PointerBuffer")
        include("org.lwjgl.BufferUtils")
    }
}

dependencies {
    lwjgl("org.lwjgl:lwjgl:3.3.0")
    lwjgl("org.lwjgl:lwjgl-tinyfd:3.3.0")
    lwjgl("org.lwjgl:lwjgl-nanovg:3.3.0")
    lwjgl("org.lwjgl:lwjgl:3.3.0:natives-windows")
    lwjgl("org.lwjgl:lwjgl-tinyfd:3.3.0:natives-windows")
    lwjgl("org.lwjgl:lwjgl-nanovg:3.3.0:natives-windows")
    lwjgl("org.lwjgl:lwjgl:3.3.0:natives-linux")
    lwjgl("org.lwjgl:lwjgl-tinyfd:3.3.0:natives-linux")
    lwjgl("org.lwjgl:lwjgl-nanovg:3.3.0:natives-linux")
    lwjgl("org.lwjgl:lwjgl:3.3.0:natives-macos")
    lwjgl("org.lwjgl:lwjgl-tinyfd:3.3.0:natives-macos")
    lwjgl("org.lwjgl:lwjgl-nanovg:3.3.0:natives-macos")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "de.tomjuri"
            artifactId = "legacylwjgl"
            version = "3.3.0"
            from(components["java"])
        }
    }
}
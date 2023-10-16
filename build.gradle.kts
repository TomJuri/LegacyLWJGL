plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("maven-publish")
}

group = "de.tomjuri"
version = "3.3.0"

repositories {
    mavenCentral()
    maven("https://libraries.minecraft.net")
    maven("https://maven.minecraftforge.net")
}

val embed: Configuration by configurations.creating
configurations.implementation.get().extendsFrom(embed)

dependencies {
    embed("org.lwjgl:lwjgl:3.3.0")
    embed("org.lwjgl:lwjgl-tinyfd:3.3.0")
    embed("org.lwjgl:lwjgl-nanovg:3.3.0")
    embed("org.lwjgl:lwjgl:3.3.0:natives-windows")
    embed("org.lwjgl:lwjgl-tinyfd:3.3.0:natives-windows")
    embed("org.lwjgl:lwjgl-nanovg:3.3.0:natives-windows")
    embed("org.lwjgl:lwjgl:3.3.0:natives-linux")
    embed("org.lwjgl:lwjgl-tinyfd:3.3.0:natives-linux")
    embed("org.lwjgl:lwjgl-nanovg:3.3.0:natives-linux")
    embed("org.lwjgl:lwjgl:3.3.0:natives-macos")
    embed("org.lwjgl:lwjgl-tinyfd:3.3.0:natives-macos")
    embed("org.lwjgl:lwjgl-nanovg:3.3.0:natives-macos")
    implementation("org.ow2.asm:asm:9.5")
    implementation("org.ow2.asm:asm-tree:9.5")
    implementation("net.minecraft:launchwrapper:1.12") { isTransitive = false }
    implementation("org.lwjgl.lwjgl:lwjgl:2.9.4-nightly-20150209") { isTransitive = false }
    implementation("net.minecraftforge:forge:1.8.9-11.15.1.2318-1.8.9:universal")
}

tasks.shadowJar {
    archiveClassifier.set("")
    configurations = listOf(embed)
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
# LegacyLWJGL
This repository provides a simple maven dependency to use LWJGL NanoVG and TinyFD 3.3.0 in a project that uses LWJGL 2.
Thanks to [DJtheRedstoner](https://github.com/DJtheRedstoner) who figured this out in his [repository](https://github.com/DJtheRedstoner/LWJGLTwoPointFive).

```kotlin
repositories {
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.TomJuri:LegacyLWJGL:<VERSION>")
}

loom {
    launchConfigs {
        getByName("client") {
            property("fml.coreMods.load", "de.tomjuri.legacylwjgl.LegacyLWJGLLoadingPlugin")
        }
    }
}

tasks {
    jar {
        manifest.attributes(
            mapOf(
                "FMLCorePlugin" to "de.tomjuri.legacylwjgl.LegacyLWJGLLoadingPlugin",
                "FMLCorePluginContainsFMLMod" to "true",
            )
        )
    }
}
```

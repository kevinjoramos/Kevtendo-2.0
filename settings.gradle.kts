plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "Kevtendo"
include(":cpu")
include(":ppu")
include(":apu")
include(":cartridge")
include(":app")
include(":common")
plugins {
    id("net.nemerosa.versioning") version "2.8.2"
}

version = versioning.info.display

subprojects {
    version = rootProject.version
}

allprojects {
    repositories {
        mavenCentral()
    }
    group = "orbe"
}

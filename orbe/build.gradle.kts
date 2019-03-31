plugins {
    java
    id("com.github.johnrengelman.shadow") version "5.0.0"
}

repositories {
    maven {
        url = uri("maven")
    }
}

dependencies {
    implementation(project(":doolin:Doolin-Core"))
    implementation(project(":doolin:Doolin-Bus"))
    implementation(project(":doolin:Doolin-Application"))
    implementation(project(":doolin:Doolin-OXML"))
    implementation(project(":doolin:Doolin-Template"))

    implementation("commons-lang:commons-lang:2.2")
    implementation("commons-logging:commons-logging:1.1")
    implementation("commons-digester:commons-digester:1.8")
    implementation("org.springframework:spring-context:2.0.7")
    // See maven/maven.bat
    implementation("com.vividsolutions:JTS:1.7.2")

    runtime("jgoodies:looks:1.2.2")

    testImplementation("junit:junit:4.12")
    testImplementation("commons-io:commons-io:1.3.1")
}

tasks.jar {
    manifest {
        attributes(
                "Main-Class" to "orbe.gui.Main"
        )
    }
}

tasks.build {
    dependsOn("shadowJar")
}

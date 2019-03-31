plugins {
    java
}

dependencies {
    implementation(project(":doolin:Doolin-Core"))
    implementation(project(":doolin:Doolin-Application"))
    implementation("velocity:velocity:1.4")
    implementation("commons-logging:commons-logging:1.1")
    implementation("commons-collections:commons-collections:3.2")
    implementation("commons-lang:commons-lang:2.2")
    implementation("org.springframework:spring-context:2.0.7")
}

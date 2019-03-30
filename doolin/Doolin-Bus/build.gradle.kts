plugins {
    java
}

dependencies {
    implementation(project(":doolin:Doolin-Core"))
    implementation("commons-logging:commons-logging:1.1")

    testImplementation("junit:junit:3.8.1")
}

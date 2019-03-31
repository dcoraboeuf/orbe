plugins {
    java
}

dependencies {
    implementation(project(":doolin:Doolin-Core"))
    implementation("commons-beanutils:commons-beanutils:1.7.0")
    implementation("commons-lang:commons-lang:2.2")

    testImplementation("junit:junit:3.8.1")
}

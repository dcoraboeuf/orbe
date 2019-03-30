plugins {
    java
}

dependencies {
    implementation(project(":doolin:Doolin-Bus"))
    implementation(project(":doolin:Doolin-Core"))
    implementation("commons-lang:commons-lang:2.2")
    implementation("org.springframework:spring-context:2.0.7")
    implementation("jgoodies:forms:1.0.5")
    implementation("javax.help:javahelp:2.0.02")
    implementation("commons-beanutils:commons-beanutils:1.7.0")
    implementation("commons-collections:commons-collections:3.2")

    testImplementation("junit:junit:3.8.1")
}

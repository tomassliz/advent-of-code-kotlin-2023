plugins {
    kotlin("jvm") version "1.9.20"
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
}

tasks {
    wrapper {
        gradleVersion = "8.5"
    }
}

dependencies {
    implementation("org.testng:testng:7.7.0")
    // Other dependencies.
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
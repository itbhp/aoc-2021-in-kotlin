plugins {
    id("org.jetbrains.kotlin.jvm") version "1.5.31"
}

repositories {
    mavenCentral()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("io.arrow-kt:arrow-core:1.0.1")
    implementation("io.arrow-kt:arrow-annotations:1.0.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2-native-mt")

    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.2")
    testImplementation("org.assertj:assertj-core:3.21.0")
    testImplementation("org.mockito:mockito-junit-jupiter:4.1.0")
}

tasks.test {
    useJUnitPlatform()
}

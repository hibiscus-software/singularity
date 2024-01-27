plugins {
  // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
  alias(libs.plugins.jvm)

  // Apply the java-library plugin for API and implementation separation.
  `java-library`
}

repositories {
  // Use Maven Central for resolving dependencies.
  mavenCentral()

  // WPILib
  maven {
    url = uri("https://frcmaven.wpi.edu/artifactory/release/")
  }

  // CTRE Phoenix 6
  maven {
    url = uri("https://maven.ctr-electronics.com/release/")
  }

  // Kauai Labs
  maven {
    url = uri("https://dev.studica.com/maven/release/2024/")
  }
}

dependencies {
  // Use the Kotlin JUnit 5 integration.
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

  // Use the JUnit 5 integration.
  testImplementation(libs.junit.jupiter.engine)

  testRuntimeOnly("org.junit.platform:junit-platform-launcher")

  // This dependency is exported to consumers, that is to say found on their compile classpath.
  api(libs.commons.math3)

  // This dependency is used internally, and not exposed to consumers on their own compile classpath.
  implementation(libs.guava)

  // WPILib
  implementation("edu.wpi.first.wpilibj:wpilibj-java:2024.2.1")
  implementation("edu.wpi.first.wpimath:wpimath-java:2024.2.1")
  implementation("edu.wpi.first.wpiutil:wpiutil-java:2024.2.1")
  implementation("edu.wpi.first.wpiunits:wpiunits-java:2024.2.1")

  // CTRE Phoenix 6
  implementation("com.ctre.phoenix6:wpiapi-java:24.1.0")

  // Kauai Labs
  implementation("com.kauailabs.navx.frc:navx-frc-java:2024.1.0")
}

// Apply a specific Java toolchain to ease working on different environments.
java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(17))
  }
}

tasks.named<Test>("test") {
  // Use JUnit Platform for unit tests.
  useJUnitPlatform()
}

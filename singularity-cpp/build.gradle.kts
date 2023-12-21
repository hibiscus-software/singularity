plugins {
  // Apply the cpp-library plugin to add support for building C++ libraries
  `cpp-library`
  // Apply the cpp-unit-test plugin to add support for building and running C++ test executables
  `cpp-unit-test`
}

repositories {
  maven {
    url = uri("https://frcmaven.wpi.edu/artifactory/release/")
  }
}

dependencies {
  implementation("edu.wpi.first.wpilibc:wpilibc-cpp:2024.1.1-beta-4")
  implementation("edu.wpi.first.wpimath:wpimath-cpp:2024.1.1-beta-4")
  implementation("edu.wpi.first.wpiutil:wpiutil-cpp:2024.1.1-beta-4")
}

library {
  // Set the target operating system and architecture for this library
  targetMachines = listOf(machines.windows.x86_64, machines.linux.x86_64)
}

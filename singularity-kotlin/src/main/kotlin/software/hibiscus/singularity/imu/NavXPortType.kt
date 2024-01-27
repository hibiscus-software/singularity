/*
 * Copyright (C), 2024 Hibiscus Software and contributors. Some rights
 * reserved. This work is licensed under the terms of the MIT license
 * which can be found in the root directory of this project.
 */

package software.hibiscus.singularity.imu

/** Enum used to store all types of ports used to connect to the NavX */
enum class NavXPortType {
  kNone,
  kSerialPort,
  kSPI,
  kI2C
}

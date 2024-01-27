/*
 * Copyright (C), 2024 Hibiscus Software and contributors. Some rights
 * reserved. This work is licensed under the terms of the MIT license
 * which can be found in the root directory of this project.
 */

package software.hibiscus.singularity.imu

import edu.wpi.first.wpilibj.I2C
import edu.wpi.first.wpilibj.SPI
import edu.wpi.first.wpilibj.SerialPort

final class NavXPort {
  lateinit var serialPort: SerialPort.Port
  lateinit var spiPort: SPI.Port
  lateinit var i2cPort: I2C.Port

  constructor() {}

  constructor(serialPort: SerialPort.Port) {
    this.serialPort = serialPort
  }

  constructor(spiPort: SPI.Port) {
    this.spiPort = spiPort
  }

  constructor(i2cPort: I2C.Port) {
    this.i2cPort = i2cPort
  }
}

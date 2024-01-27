/*
 * Copyright (C), 2024 Hibiscus Software and contributors. Some rights
 * reserved. This work is licensed under the terms of the MIT license
 * which can be found in the root directory of this project.
 */

package software.hibiscus.singularity.imu

import edu.wpi.first.wpilibj.I2C
import edu.wpi.first.wpilibj.SPI
import edu.wpi.first.wpilibj.SerialPort

/** Class used to store which port the NavX is connected to. */
final class NavXPort {
  /** The serial port of the NavX. */
  lateinit var serialPort: SerialPort.Port

  /** The SPI port of the NavX. */
  lateinit var spiPort: SPI.Port

  /** The I2C port of the NavX. */
  lateinit var i2cPort: I2C.Port

  /**
   * Constructor for the [NavXPort] class.
   * @param serialPort The serial port of the NavX as a
   * [SerialPort.Port](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/wpilibj/SerialPort.Port.html).
   */
  constructor(serialPort: SerialPort.Port) {
    this.serialPort = serialPort
  }

  /**
   * Constructor for the [NavXPort] class.
   * @param spiPort The SPI port of the NavX as a
   * [SPI.Port](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/wpilibj/SPI.Port.html).
   */
  constructor(spiPort: SPI.Port) {
    this.spiPort = spiPort
  }

  /**
   * Constructor for the [NavXPort] class.
   * @param i2cPort The I2C port of the NavX as a
   * [I2C.Port](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/wpilibj/I2C.Port.html).
   */
  constructor(i2cPort: I2C.Port) {
    this.i2cPort = i2cPort
  }
}

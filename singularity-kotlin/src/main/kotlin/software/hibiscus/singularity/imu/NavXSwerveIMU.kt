/*
 * Copyright (C), 2024 Hibiscus Software and contributors. Some rights
 * reserved. This work is licensed under the terms of the MIT license
 * which can be found in the root directory of this project.
 */

package software.hibiscus.singularity.imu

import com.kauailabs.navx.frc.AHRS
import edu.wpi.first.math.geometry.Rotation3d
import edu.wpi.first.math.geometry.Translation3d
import edu.wpi.first.wpilibj.I2C
import edu.wpi.first.wpilibj.SPI
import edu.wpi.first.wpilibj.SerialPort
import java.util.Optional
import software.hibiscus.singularity.util.Unimplemented

/** Class used to represent the Kauai Labs NavX IMU. */
class NavXSwerveIMU : SwerveIMU {
  /** The instantiated NavX */
  private lateinit var navX: AHRS

  /** 
   * The offset of the NavX as a
   * [Rotation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation3d.html).
   */
  override var offset: Rotation3d = Rotation3d()

  /** Whether the NavX is inverted. */
  override var isInverted: Boolean = false

  /** NavX has no equivalent field. */
  @Unimplemented("NavX has no Equivalent field") override var hasConfigChanged: Boolean = false

  /**
   * Constructor for the [NavXSwerveIMU] class.
   * 
   * @param serialPort The serial port of the NavX as a
   * [SerialPort.Port](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/wpilibj/SerialPort.Port.html).
   */
  constructor(serialPort: SerialPort.Port) {
    try {
      navX = AHRS(serialPort)
    } catch (exception: RuntimeException) {
      println("[ERROR]: Error Instantiating NavX: " + exception.message)
    }

    factoryDefaults()
  }

  /**
   * Constructor for the [NavXSwerveIMU] class.
   * 
   * @param spiPort The SPI port of the NavX as a
   * [SPI.Port](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/wpilibj/SPI.Port.html).
   */
  constructor(spiPort: SPI.Port) {
    try {
      navX = AHRS(spiPort)
    } catch (exception: RuntimeException) {
      println("[ERROR]: Error Instantiating NavX: " + exception.message)
    }

    factoryDefaults()
  }

  /**
   * Constructor for the [NavXSwerveIMU] class.
   * 
   * @param i2cPort The I2C port of the NavX as a
   * [I2C.Port](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/wpilibj/I2C.Port.html).
   */
  constructor(i2cPort: I2C.Port) {
    try {
      navX = AHRS(i2cPort)
    } catch (exception: RuntimeException) {
      println("[ERROR]: Error Instantiating NavX: " + exception.message)
    }

    factoryDefaults()
  }

  /**
   * Sets the offset of the NavX.
   *
   * @param offset The offset of the NavX as a
   * [Rotation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation3d.html).
   */
  override fun setOffset(offset: Rotation3d) {
    this.offset = offset
  }

  /**
   * Sets the inversion of the NavX.
   *
   * @param isInverted Whether the NavX is inverted.
   */
  override fun setInverted(isInverted: Boolean) {
    this.isInverted = isInverted
  }

  /**
   * Gets the
   * [Rotation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation3d.html)
   * from the NavX without zeroing.
   *
   * @return The [Rotation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation3d.html)
   * from the NavX.
   */
  override fun getRawRotation3d(): Rotation3d {
    if (isInverted) return navX.rotation3d.unaryMinus() else return navX.rotation3d
  }

  /**
   * Gets the
   * [Rotation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation3d.html)
   * from the NavX.
   *
   * @return The [Rotation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation3d.html)
   * from the NavX.
   */
  override fun getRotation3d(): Rotation3d {
    return getRawRotation3d().minus(offset)
  }

  /**
   * Get's the
   * [Translation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Translation3d.html)
   * from the NavX in meters per second squared.
   *
   * @return The [Translation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Translation3d.html)
   * from the NavX.
   */
  override fun getAcceleration(): Optional<Translation3d> {
    return Optional.of(
        Translation3d(
            navX.worldLinearAccelX.toDouble(),
            navX.worldLinearAccelY.toDouble(),
            navX.worldLinearAccelZ.times(9.81)
        )
    )
  }

  /** NavX has no equivalent function. */
  @Unimplemented("NavX has no Equivalent Function") override fun burnConfig() {}

  /** Resets the NavX to factory defaults. */
  override fun factoryDefaults() {
    navX.reset()
  }

  /** NavX has no equivalent function. */
  @Unimplemented("NavX has no Equivalent Function") override fun clearFaults() {}

  /**
   * Gets the instantiated NavX object.
   *
   * @return The instantiated NavX object.
   */
  override fun getIMU(): Any {
    return navX
  }
}

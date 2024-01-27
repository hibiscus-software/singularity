/*
 * Copyright (C), 2024 Hibiscus Software and contributors. Some rights
 * reserved. This work is licensed under the terms of the MIT license
 * which can be found in the root directory of this project.
 */

package software.hibiscus.singularity.imu

import com.ctre.phoenix6.configs.Pigeon2Configuration
import com.ctre.phoenix6.hardware.Pigeon2
import edu.wpi.first.math.geometry.Rotation3d
import edu.wpi.first.math.geometry.Translation3d
import java.util.Optional
import java.util.function.Supplier

/** Class used to represent the Cross the Road Electronics Pigeon 2.0 IMU. */
class Pigeon2SwerveIMU : SwerveIMU {
  /** The instantiated Pigeon 2.0. */
  private var pigeon2: Pigeon2

  /** The configuration of the Pigeon 2.0. */
  private var pigeon2Config = Pigeon2Configuration()

  /**
   * The offset of the Pigeon 2.0 as a
   * [Rotation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation3d.html).
   */
  override var offset: Rotation3d = Rotation3d()

  /** Whether the Pigeon 2.0 is inverted. */
  override var isInverted: Boolean = false

  /** Whether the configuration has changed. */
  override var hasConfigChanged: Boolean = false

  /**
   * Constructor for the [Pigeon2SwerveIMU] class.
   * 
   * @param canId The CAN Id of the Pigeon 2.0.
   */
  constructor(canId: Int) {
    pigeon2 = Pigeon2(canId)

    factoryDefaults()
    clearFaults()
  }

  /**
   * Constructor for the [Pigeon2SwerveIMU] class.
   * 
   * @param canId The CAN Id of the Pigeon 2.0.
   * @param canBusName The name of the CAN bus of the Pigeon 2.0.
   */
  constructor(canId: Int, canBusName: String) {
    pigeon2 = Pigeon2(canId, canBusName)

    factoryDefaults()
    clearFaults()
  }

  /**
   * Sets the offset of the Pigeon 2.0.
   *
   * @param offset The offset of the Pigeon 2.0 as a
   * [Rotation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation3d.html).
   */
  override fun setOffset(offset: Rotation3d) {
    this.offset = offset
  }

  /**
   * Sets the inversion of the Pigeon 2.0.
   *
   * @param isInverted Whether the Pigeon 2.0 is inverted.
   */
  override fun setInverted(isInverted: Boolean) {
    this.isInverted = isInverted
  }

  /**
   * Gets the
   * [Rotation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation3d.html)
   * from the Pigeon 2.0 without zeroing.
   *
   * @return The [Rotation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation3d.html)
   * from the Pigeon 2.0.
   */
  override fun getRawRotation3d(): Rotation3d {
    if (isInverted) return pigeon2.rotation3d.unaryMinus() else return pigeon2.rotation3d
  }

  /**
   * Gets the
   * [Rotation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation3d.html)
   * from the Pigeon 2.0.
   *
   * @return The [Rotation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation3d.html)
   * from the Pigeon 2.0.
   */
  override fun getRotation3d(): Rotation3d {
    return getRawRotation3d().minus(offset)
  }

  /**
   * Get's the
   * [Translation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Translation3d.html)
   * from the Pigeon 2.0 in meters per second squared.
   *
   * @return The [Translation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Translation3d.html)
   * from the Pigeon 2.0.
   */
  override fun getAcceleration(): Optional<Translation3d> {
    var xAcceleration: Supplier<Double> = pigeon2.accelerationX.refresh().asSupplier()
    var yAcceleration: Supplier<Double> = pigeon2.accelerationY.refresh().asSupplier()
    var zAcceleration: Supplier<Double> = pigeon2.accelerationZ.refresh().asSupplier()

    return Optional.of(
        Translation3d(
            xAcceleration.get(),
            yAcceleration.get(),
            zAcceleration.get().times(9.81 / 16384.0)
        )
    )
  }

  /** Burns the configuration to the Pigeon 2.0. */
  override fun burnConfig() {
    if (hasConfigChanged) {
      pigeon2.getConfigurator().apply(pigeon2Config)

      hasConfigChanged = false
    }
  }

  /** Resets the Pigeon 2.0 to factory defaults. */
  override fun factoryDefaults() {
    pigeon2Config = Pigeon2Configuration()
    
    hasConfigChanged = true
    burnConfig()
  }

  /** Clears faults on the Pigeon 2.0. */
  override fun clearFaults() {
    pigeon2.clearStickyFaults()
  }

  /**
   * Gets the instantiated Pigeon 2.0 object.
   *
   * @return The instantiated Pigeon 2.0 object.
   */
  override fun getIMU(): Any {
    return pigeon2
  }
}

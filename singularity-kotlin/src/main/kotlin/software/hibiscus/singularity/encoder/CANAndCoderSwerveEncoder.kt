/*
 * Copyright (C), 2024 Hibiscus Software and contributors. Some rights
 * reserved. This work is licensed under the terms of the MIT license
 * which can be found in the root directory of this project.
 */

package software.hibiscus.singularity.encoder

import com.reduxrobotics.sensors.canandcoder.Canandcoder
import edu.wpi.first.math.geometry.Rotation2d
import software.hibiscus.singularity.util.Unimplemented

/** Class used to represent the Redux Robotics CANAndCoder encoder. */
class CANAndCoderSwerveEncoder : SwerveEncoder {
  /** The instantiated CANAndCoder. */
  private var canAndCoder: Canandcoder

  /** The configuration of the CANAndCoder. */
  private var canAndCoderConfig: Canandcoder.Settings = Canandcoder.Settings()

  /**
   * The offset of the CANAndCoder as a
   * [Rotation2d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation2d.html).
   */
  override var offset: Rotation2d = Rotation2d()

  /** Whether the CANAndCoder is inverted. */
  override var isInverted: Boolean = false

  /**CANAndCoder has no equivalent field. */
  @Unimplemented("CANAndCoder has no Equivalent field") override var hasConfigChanged: Boolean = false

  /**
   * Constructor for the [CANAndCoderSwerveEncoder] class.
   * 
   * @param canId The CAN Id of the CANAndCoder.
   */
  constructor(canId: Int) {
    canAndCoder = Canandcoder(canId)

    factoryDefaults()
    clearFaults()
  }

  /**
   * Sets the offset of the CANAndCoder.
   *
   * @param offset The offset of the CANAndCoder as a
   * [Rotation2d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation2d.html).
   */
  override fun setOffset(offset: Rotation2d) {
    this.offset = offset

    canAndCoder.setSettings(canAndCoderConfig.setZeroOffset(offset.degrees))
  }

  /**
   * Sets the inversion of the CANAndCoder.
   *
   * @param isInverted Whether the CANAndCoder is inverted.
   */
  override fun setInverted(isInverted: Boolean) {
    this.isInverted = isInverted

    if (isInverted) {
      canAndCoder.setSettings(canAndCoderConfig.setInvertDirection(true))
    } else {
      canAndCoder.setSettings(canAndCoderConfig.setInvertDirection(false))
    }
  }

  /**
   * Get's the position from the CANAndCoder in degrees.
   * 
   * @return The position from the CANAndCoder in degrees.
   */
  override fun getPosition(): Double {
    return canAndCoder.getAbsPosition() * 360
  }

  /**
   * Get's the velocity from the CANAndCoder in degrees per second.
   * 
   * @return The velocity from the CANAndCoder in degrees per second.
   */
  override fun getVelocity(): Double {
    return canAndCoder.getVelocity() * 360
  }

  /** CANAndCoder has no equivalent function. */
  @Unimplemented("CANAndCoder has no Equivalent Function") override fun burnConfig() {}

  /** Resets the CANAndCoder to factory defaults. */
  override fun factoryDefaults() {
    canAndCoder.resetFactoryDefaults(false);
  }

  /** Clears faults on the CANAndCoder. */
  override fun clearFaults() {
    canAndCoder.clearStickyFaults()
  }

  /**
   * Gets the instantiated CANAndCoder object.
   *
   * @return The instantiated CANAndCoder object.
   */
  override fun getEncoder(): Any {
    return canAndCoder
  }
}

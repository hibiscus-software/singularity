/*
 * Copyright (C), 2024 Hibiscus Software and contributors. Some rights
 * reserved. This work is licensed under the terms of the MIT license
 * which can be found in the root directory of this project.
 */

package software.hibiscus.singularity.encoder

import com.ctre.phoenix6.hardware.CANcoder
import com.ctre.phoenix6.configs.CANcoderConfiguration
import com.ctre.phoenix6.signals.SensorDirectionValue
import edu.wpi.first.math.geometry.Rotation2d

/** Class used to represent the Cross the Road Electronics CANCoder encoder. */
class CANCoderSwerveEncoder : SwerveEncoder {
  /** The instantiated CANCoder. */
  private var canCoder: CANcoder

  /** The configuration of the CANCoder. */
  private var canCoderConfig: CANcoderConfiguration = CANcoderConfiguration()

  /**
   * The offset of the CANCoder as a
   * [Rotation2d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation2d.html).
   */
  override var offset: Rotation2d = Rotation2d()

  /** Whether the CANCoder is inverted. */
  override var isInverted: Boolean = false

  /** Whether the configuration has changed. */
  override var hasConfigChanged: Boolean = false

  /**
   * Constructor for the [CANCoderSwerveEncoder] class.
   * 
   * @param canId The CAN Id of the CANCoder.
   */
  constructor(canId: Int) {
    canCoder = CANcoder(canId)

    factoryDefaults()
    clearFaults()
  }

  /**
   * Constructor for the [CANCoderSwerveEncoder] class.
   * 
   * @param canId The CAN Id of the CANCoder.
   * @param canBusName The name of the CAN bus of the CANCoder.
   */
  constructor(canId: Int, canBusName: String) {
    canCoder = CANcoder(canId, canBusName)

    factoryDefaults()
    clearFaults()
  }

  /**
   * Sets the offset of the CANCoder.
   *
   * @param offset The offset of the CANCoder as a
   * [Rotation2d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation2d.html).
   */
  override fun setOffset(offset: Rotation2d) {
    this.offset = offset

    canCoderConfig.MagnetSensor.MagnetOffset = offset.getDegrees()

    hasConfigChanged = true
    burnConfig()
  }

  /**
   * Sets the inversion of the CANCoder.
   *
   * @param isInverted Whether the CANCoder is inverted.
   */
  override fun setInverted(isInverted: Boolean) {
    this.isInverted = isInverted

    if (isInverted) {
      canCoderConfig.MagnetSensor.SensorDirection = SensorDirectionValue.CounterClockwise_Positive
    } else {
      canCoderConfig.MagnetSensor.SensorDirection = SensorDirectionValue.Clockwise_Positive
    }

    hasConfigChanged = true
    burnConfig()
  }

  /**
   * Get's the position from the CANCoder in degrees.
   * 
   * @return The position from the CANCoder in degrees.
   */
  override fun getPosition(): Double {
    return canCoder.getPosition().valueAsDouble * 360
  }

  /**
   * Get's the velocity from the CANCoder in degrees per second.
   * 
   * @return The velocity from the CANCoder in degrees per second.
   */
  override fun getVelocity(): Double {
    return canCoder.getVelocity().valueAsDouble * 360
  }

  /** Burns the configuration to the CANCoder. */
  override fun burnConfig() {
    if (hasConfigChanged) {
      canCoder.getConfigurator().apply(canCoderConfig)

      hasConfigChanged = false
    }
  }

  /** Resets the CANCoder to factory defaults. */
  override fun factoryDefaults() {
    canCoderConfig = CANcoderConfiguration()
    
    hasConfigChanged = true
    burnConfig()
  }

  /** Clears faults on the CANCoder. */
  override fun clearFaults() {
    canCoder.clearStickyFaults()
  }

   /**
   * Gets the instantiated CANCoder object.
   *
   * @return The instantiated CANCoder object.
   */
  override fun getEncoder(): Any {
    return canCoder
  }
}

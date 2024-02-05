/*
 * Copyright (C), 2024 Hibiscus Software and contributors. Some rights
 * reserved. This work is licensed under the terms of the MIT license
 * which can be found in the root directory of this project.
 */

package software.hibiscus.singularity.encoder

import edu.wpi.first.math.geometry.Rotation2d

/** Abstract class used to represent a generic encoder. */
abstract class SwerveEncoder {
  /**
   * The offset of the encoder as a
   * [Rotation2d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation2d.html).
   */
  abstract var offset: Rotation2d

  /** Whether the encoder is inverted. */
  abstract var isInverted: Boolean

  /** Whether the configuration has changed. */
  abstract var hasConfigChanged: Boolean

  /** Enum used to store all types of Encoders that can be used with a swerve drive robot. */
  enum class SwerveEncoderType {
    kNone,
    kCANAndCoder,
    kCANCoder,
  }

  /**
   * Sets the offset of the encoder.
   *
   * @param offset The offset of the encoder as a
   * [Rotation2d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation2d.html).
   */
  abstract fun setOffset(offset: Rotation2d)

  /**
   * Sets the inversion of the encoder.
   *
   * @param isInverted Whether the encoder is inverted.
   */
  abstract fun setInverted(isInverted: Boolean)

  /**
   * Get's the position from the encoder in degrees.
   * 
   * @return The position from the encoder in degrees.
   */
  abstract fun getPosition(): Double

  /**
   * Get's the velocity from the encoder in degrees per second.
   * 
   * @return The velocity from the encoder in degrees per second.
   */
  abstract fun getVelocity(): Double

  /** Burns the configuration to the encoder. */
  abstract fun burnConfig()

  /** Resets the encoder to factory defaults. */
  abstract fun factoryDefaults()

  /** Clears faults on the encoder. */
  abstract fun clearFaults()

  /**
   * Gets the instantiated encoder object.
   *
   * @return The instantiated encoder object.
   */
  abstract fun getEncoder(): Any
}

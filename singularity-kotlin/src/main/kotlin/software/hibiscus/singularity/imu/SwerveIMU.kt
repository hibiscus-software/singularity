/*
 * Copyright (C), 2024 Hibiscus Software and contributors. Some rights
 * reserved. This work is licensed under the terms of the MIT license
 * which can be found in the root directory of this project.
 */

package software.hibiscus.singularity.imu

import edu.wpi.first.math.geometry.Rotation3d
import edu.wpi.first.math.geometry.Translation3d
import java.util.Optional

/** Abstract class used to represent a generic IMU. */
abstract class SwerveIMU {
  /**
   * The offset of the IMU as a
   * [Rotation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation3d.html).
   */
  abstract var offset: Rotation3d

  /** Whether the IMU is inverted. */
  abstract var isInverted: Boolean

  /** Whether the configuration has changed. */
  abstract var hasConfigChanged: Boolean

  /**
   * Sets the offset of the IMU.
   *
   * @param offset The offset of the IMU as a
   * [Rotation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation3d.html).
   */
  abstract fun setOffset(offset: Rotation3d)

  /**
   * Sets the inversion of the IMU.
   *
   * @param isInverted Whether the IMU is inverted.
   */
  abstract fun setInverted(isInverted: Boolean)

  /**
   * Gets the
   * [Rotation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation3d.html)
   * from the IMU without zeroing.
   *
   * @return The [Rotation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation3d.html)
   * from the IMU.
   */
  abstract fun getRawRotation3d(): Rotation3d

  /**
   * Gets the
   * [Rotation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation3d.html)
   * from the IMU.
   *
   * @return The [Rotation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation3d.html)
   * from the IMU.
   */
  abstract fun getRotation3d(): Rotation3d

  /**
   * Get's the
   * [Translation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Translation3d.html)
   * from the IMU in meters per second squared.
   *
   * @return The [Translation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Translation3d.html)
   * from the IMU.
   */
  abstract fun getAcceleration(): Optional<Translation3d>

  /** Burns the configuration to the IMU. */
  abstract fun burnConfig()

  /** Resets the IMU to factory defaults. */
  abstract fun factoryDefaults()

  /** Clears faults on the IMU. */
  abstract fun clearFaults()

  /**
   * Gets the instantiated IMU object.
   *
   * @return The instantiated IMU object.
   */
  abstract fun getIMU(): Any
}

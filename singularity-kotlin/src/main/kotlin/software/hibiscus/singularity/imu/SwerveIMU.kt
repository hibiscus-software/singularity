/*
 * Copyright (C), 2024 Hibiscus Software and contributors. Some rights
 * reserved. This work is licensed under the terms of the MIT license
 * which can be found in the root directory of this project.
 */

package software.hibiscus.singularity.imu

import edu.wpi.first.math.geometry.Rotation3d
import edu.wpi.first.math.geometry.Translation3d
import java.util.Optional

abstract class SwerveIMU() {
  /** The offset of the IMU as a {@link Rotation3d}. */
  abstract var offset: Rotation3d

  /** Whether the IMU is inverted. */
  abstract var isInverted: Boolean

  /** Whether the configuration has changed. */
  abstract var hasConfigChanged: Boolean

  /**
   * Sets the offset of the IMU.
   *
   * @param offset The offset of the IMU as a {@link Rotation3d}.
   */
  abstract fun setOffset(offset: Rotation3d)

  /**
   * Sets the inversion of the IMU.
   *
   * @param isInverted Whether the IMU is inverted.
   */
  abstract fun setInverted(isInverted: Boolean)

  /**
   * Gets the {@link Rotation3d} from the IMU without zeroing.
   *
   * @return The {@link Rotation3d} from the IMU.
   */
  abstract fun getRawRotation3d(): Rotation3d

  /**
   * Gets the {@link Rotation3d} from the IMU.
   *
   * @return The {@link Rotation3d} from the IMU.
   */
  abstract fun getRotation3d(): Rotation3d

  /**
   * Get's the {@link Translation3d} from the IMU in meters per second squared.
   *
   * @return The {@link Translation3d} from the IMU.
   */
  abstract fun getAcceleration(): Optional<Translation3d>

  /** Burns the configuration to the IMU. */
  abstract fun burnConfig()

  /** Reset IMU to factory defaults. */
  abstract fun factoryDefaults()

  /** Clear faults on the IMU. */
  abstract fun clearFaults()

  /**
   * Gets the instantiated IMU object.
   *
   * @return The instantiated IMU object.
   */
  abstract fun getIMU(): Any
}

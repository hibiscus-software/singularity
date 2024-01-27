/*
 * Copyright (C), 2024 Hibiscus Software and contributors. Some rights
 * reserved. This work is licensed under the terms of the MIT license
 * which can be found in the root directory of this project.
 */

package software.hibiscus.singularity.imu

import com.kauailabs.navx.frc.AHRS
import edu.wpi.first.math.geometry.Rotation3d
import edu.wpi.first.math.geometry.Translation3d
import java.util.Optional
import software.hibiscus.singularity.util.Unimplemented

class NavXSwerveIMU(navX: AHRS) : SwerveIMU() {
  /** The instantiated NavX */
  private var navX: AHRS

  /** The offset of the NavX as a {@link Rotation3d}. */
  override var offset: Rotation3d = Rotation3d()

  /** Whether the NavX is inverted. */
  override var isInverted: Boolean = false

  @Unimplemented("NavX has no configuration API") override var hasConfigChanged: Boolean = false

  init {
    this.navX = navX

    factoryDefaults()
  }

  /**
   * Sets the offset of the NavX.
   *
   * @param offset The offset of the NavX as a {@link Rotation3d}.
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
   * Gets the {@link Rotation3d} from the NavX without zeroing.
   *
   * @return The {@link Rotation3d} from the NavX.
   */
  override fun getRawRotation3d(): Rotation3d {
    if (isInverted) return navX.rotation3d.unaryMinus() else return navX.rotation3d
  }

  /**
   * Gets the {@link Rotation3d} from the NavX.
   *
   * @return The {@link Rotation3d} from the NavX.
   */
  override fun getRotation3d(): Rotation3d {
    return getRawRotation3d().minus(offset)
  }

  /**
   * Get's the {@link Translation3d} from the NavX in meters per second squared.
   *
   * @return The {@link Translation3d} from the NavX.
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

  @Unimplemented("NavX has no configuration API") override fun burnConfig() {}

  /** Reset NavX to factory defaults. */
  override fun factoryDefaults() {
    navX.reset()
  }

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

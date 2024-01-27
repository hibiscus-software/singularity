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

class Pigeon2SwerveIMU(pigeon2: Pigeon2) : SwerveIMU() {
  /** The instantiated Pigeon 2. */
  private var pigeon2: Pigeon2

  /** The configuration of the Pigeon 2. */
  private var pigeon2Config = Pigeon2Configuration()

  /** The offset of the Pigeon 2 as a {@link Rotation3d}. */
  override var offset: Rotation3d = Rotation3d()

  /** Whether the Pigeon 2 is inverted. */
  override var isInverted: Boolean = false

  /** Whether the configuration has changed. */
  override var hasConfigChanged: Boolean = false

  init {
    this.pigeon2 = pigeon2

    factoryDefaults()
    clearFaults()
  }

  /**
   * Sets the offset of the Pigeon 2.
   *
   * @param offset The offset of the Pigeon 2 as a {@link Rotation3d}.
   */
  override fun setOffset(offset: Rotation3d) {
    this.offset = offset
  }

  /**
   * Sets the inversion of the Pigeon 2.
   *
   * @param isInverted Whether the Pigeon 2 is inverted.
   */
  override fun setInverted(isInverted: Boolean) {
    this.isInverted = isInverted
  }

  /**
   * Gets the {@link Rotation3d} from the Pigeon 2 without zeroing.
   *
   * @return The {@link Rotation3d} from the Pigeon 2.
   */
  override fun getRawRotation3d(): Rotation3d {
    if (isInverted) return pigeon2.rotation3d.unaryMinus() else return pigeon2.rotation3d
  }

  /**
   * Gets the {@link Rotation3d} from the Pigeon 2.
   *
   * @return The {@link Rotation3d} from the Pigeon 2.
   */
  override fun getRotation3d(): Rotation3d {
    return getRawRotation3d().minus(offset)
  }

  /**
   * Get's the {@link Translation3d} from the Pigeon 2 in meters per second squared.
   *
   * @return The {@link Translation3d} from the Pigeon 2.
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

  /** Burns the configuration to the Pigeon 2. */
  override fun burnConfig() {
    if (hasConfigChanged) {
      pigeon2.getConfigurator().apply(pigeon2Config)
      hasConfigChanged = false
    }
  }

  /** Reset Pigeon 2 to factory defaults. */
  override fun factoryDefaults() {
    pigeon2Config = Pigeon2Configuration()
    hasConfigChanged = true
  }

  /** Clear faults on the Pigeon 2. */
  override fun clearFaults() {
    pigeon2.clearStickyFaults()
  }

  /**
   * Gets the instantiated Pigeon 2 object.
   *
   * @return The instantiated Pigeon 2 object.
   */
  override fun getIMU(): Any {
    return pigeon2
  }
}

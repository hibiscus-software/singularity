/*
 * Copyright (C), 2024 Hibiscus Software and contributors. Some rights
 * reserved. This work is licensed under the terms of the MIT license
 * which can be found in the root directory of this project.
 */

#pragma once

#include <frc/geometry/Rotation3d.h>
#include <frc/geometry/Translation3d.h>

#include <any>
#include <optional>

namespace singularity {
/** Abstract class used to represent a generic IMU. */
struct SwerveIMU {
 public:
  /**
   * The offset of the IMU as a
   * [Rotation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation3d.html).
   */
  Rotation3d offset;

  /** Whether the IMU is inverted. */
  bool isInverted;

  /** Whether the configuration has changed. */
  bool hasConfigChanged;

  /**
   * Sets the offset of the IMU.
   *
   * @param offset The offset of the IMU as a
   * [Rotation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation3d.html).
   */
  void SetOffset(Rotation3d offset);

  /**
   * Sets the inversion of the IMU.
   *
   * @param isInverted Whether the IMU is inverted.
   */
  void SetInverted(bool isInverted);

  /**
   * Gets the
   * [Rotation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation3d.html)
   * from the IMU without zeroing.
   *
   * @return The [Rotation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation3d.html)
   * from the IMU.
   */
  Rotation3d GetRawRotation3d();

  /**
   * Gets the
   * [Rotation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation3d.html)
   * from the IMU.
   *
   * @return The [Rotation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation3d.html)
   * from the IMU.
   */
  Rotation3d GetRotation3d();

  /**
   * Get's the
   * [Translation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Translation3d.html)
   * from the IMU in meters per second squared.
   *
   * @return The [Translation3d](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Translation3d.html)
   * from the IMU.
   */
  std::optional<Translation3d> GetAcceleration();

  /** Burns the configuration to the IMU. */
  void BurnConfig();

  /** Resets the IMU to factory defaults. */
  void FactoryDefaults();

  /** Clears faults on the IMU. */
  void ClearFaults();

  /**
   * Gets the instantiated IMU object.
   *
   * @return The instantiated IMU object.
   */
  std::any GetIMU();
};
}  // namespace singularity

/*
 * Copyright (C), 2023 Codex Microsystems. Some rights reserved. This work is
 * licensed under the terms of the MIT license which can be found in the
 * root directory of this project.
 */

package systems.codexmicro.singularity

import edu.wpi.first.math.geometry.Rotation2d

final class SwerveModuleConstants() {
  var driveMotorId: Int = 0
  var angleMotorId: Int = 0
  var encoderId: Int = 0

  var angleOffset: Rotation2d = Rotation2d()

  init {}

  fun withDriveMotorId(driveMotorId: Int): SwerveModuleConstants {
    this.driveMotorId = driveMotorId
    return this
  }

  fun withAngleMotorId(angleMotorId: Int): SwerveModuleConstants {
    this.angleMotorId = angleMotorId
    return this
  }

  fun withEncoderId(encoderId: Int): SwerveModuleConstants {
    this.encoderId = encoderId
    return this
  }

  fun withAngleOffset(angleOffset: Rotation2d): SwerveModuleConstants {
    this.angleOffset = angleOffset
    return this
  }
}

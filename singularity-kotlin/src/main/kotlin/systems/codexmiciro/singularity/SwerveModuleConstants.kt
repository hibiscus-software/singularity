/*
 * Copyright (C), 2023 Codex Microsystems and contributors. Some rights
 * reserved. This work is licensed under the terms of the MIT license
 * which can be found in the root directory of this project.
 */

package systems.codexmicro.singularity

import edu.wpi.first.math.geometry.Rotation2d

final class SwerveModuleConstants(
    driveMotorId: Int,
    angleMotorId: Int,
    encoderId: Int,
    angleOffset: Rotation2d
) {
  var driveMotorId: Int
  var angleMotorId: Int
  var encoderId: Int

  var angleOffset: Rotation2d

  init {
    this.driveMotorId = driveMotorId
    this.angleMotorId = angleMotorId
    this.encoderId = encoderId

    this.angleOffset = angleOffset
  }
}

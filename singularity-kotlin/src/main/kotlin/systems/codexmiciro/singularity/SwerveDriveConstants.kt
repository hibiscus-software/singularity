/*
 * Copyright (C), 2023 Codex Microsystems. Some rights reserved. This work is
 * licensed under the terms of the MIT license which can be found in the
 * root directory of this project.
 */

package systems.codexmicro.singularity

final class SwerveDriveConstants(gyroId: Int, wheelBaseMeters: Double, trackWidthMeters: Double) {
  var gyroId: Int

  var wheelBaseMeters: Double
  var trackWidthMeters: Double

  init {
    this.gyroId = gyroId

    this.wheelBaseMeters = wheelBaseMeters
    this.trackWidthMeters = trackWidthMeters
  }
}

/*
 * Copyright (C), 2023 Codex Microsystems and contributors. Some rights
 * reserved. This work is licensed under the terms of the MIT license
 * which can be found in the root directory of this project.
 */

package systems.codexmicro.singularity

import edu.wpi.first.units.Distance
import edu.wpi.first.units.Measure

final class SwerveDriveConstants(gyroId: Int, wheelBaseMeters: Measure<Distance>, trackWidthMeters: Measure<Distance>) {
  var gyroId: Int

  var wheelBaseMeters: Measure<Distance>
  var trackWidthMeters: Measure<Distance>

  init {
    this.gyroId = gyroId

    this.wheelBaseMeters = wheelBaseMeters
    this.trackWidthMeters = trackWidthMeters
  }
}

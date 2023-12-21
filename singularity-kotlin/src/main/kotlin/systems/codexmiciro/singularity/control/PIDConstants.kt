/*
 * Copyright (C), 2023 Codex Microsystems. Some rights reserved. This work is
 * licensed under the terms of the MIT license which can be found in the
 * root directory of this project.
 */

package systems.codexmicro.singularity.control

final class PIDConstants(kP: Double, kI: Double, kD: Double) {
  var kP: Double
  var kI: Double
  var kD: Double

  init {
    this.kP = kP
    this.kI = kI
    this.kD = kD
  }
}

/*
 * Copyright (C), 2024 Hibiscus Software and contributors. Some rights
 * reserved. This work is licensed under the terms of the MIT license
 * which can be found in the root directory of this project.
 */

package software.hibiscus.singularity.control

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

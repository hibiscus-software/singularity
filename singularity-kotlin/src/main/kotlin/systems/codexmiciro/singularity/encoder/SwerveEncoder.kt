/*
 * Copyright (C), 2023 Codex Microsystems. Some rights reserved. This work is
 * licensed under the terms of the MIT license which can be found in the
 * root directory of this project.
 */

package systems.codexmicro.singularity.encoder

abstract class SwerveEncoder() {
  abstract fun getPosition(): Double

  abstract fun getVelocity(): Double

  abstract fun setOffset(offset: Double)

  abstract fun setInverted(isInverted: Boolean)

  abstract fun burnConfig()

  abstract fun factoryDefaults()

  abstract fun clearFaults()

  abstract fun getEncoder(): Any
}

/*
 * Copyright (C), 2023 Codex Microsystems. Some rights reserved. This work is
 * licensed under the terms of the MIT license which can be found in the
 * root directory of this project.
 */

package systems.codexmicro.singularity.motor

import systems.codexmicro.singularity.control.PIDConstants

abstract class SwerveMotor() {
  abstract var isDriveMotor: Boolean

  abstract fun set(percentOutput: Double)

  abstract fun setVoltage(voltsOutput: Double)

  abstract fun setReference(setpoint: Double, feedforward: Double)

  abstract fun setReference(setpoint: Double, feedforward: Double, position: Double)

  abstract fun setInverted(isInverted: Boolean)

  abstract fun setMotorBrake(isBrakeMode: Boolean)

  abstract fun setVoltageCompensation(nominalVoltage: Double)

  abstract fun setCurrentLimit(currentLimit: Double)

  abstract fun setLoopRampRate(rampRate: Double)

  abstract fun setEncoderPosition(position: Double)

  abstract fun getEncoderPosition(): Double

  abstract fun getEncoderVelocity(): Double

  abstract fun configPID(pidConstants: PIDConstants)

  abstract fun configPIDWrapping()

  abstract fun burnConfig()

  abstract fun factoryDefaults()

  abstract fun clearFaults()

  abstract fun getMotor(): Any
}

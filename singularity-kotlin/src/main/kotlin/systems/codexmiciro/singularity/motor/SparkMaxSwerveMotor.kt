/*
 * Copyright (C), 2023 Codex Microsystems. Some rights reserved. This work is
 * licensed under the terms of the MIT license which can be found in the
 * root directory of this project.
 */

package systems.codexmicro.singularity.motor

class SparkMaxSwerveMotor() : SwerveMotor() {

  override val isDriveMotor: Boolean = TODO("SET VALUE")

  override fun set(percentOutput: Double) {}

  override fun setVoltage(voltsOutput: Double) {}

  override fun setReference(setpoint: Double, feedforward: Double) {}

  override fun setReference(setpoint: Double, feedforward: Double, position: Double) {}

  override fun setInverted(isInverted: Boolean) {}

  override fun setMotorBrake(isBrakeMode: Boolean) {}

  override fun setVoltageCompensation(nominalVoltage: Double) {}

  override fun setCurrentLimit(currentLimit: Double) {}

  override fun setLoopRampRate(rampRate: Double) {}

  override fun setEncoderPosition(position: Double) {}

  override fun getEncoderPosition(): Double {}

  override fun getEncoderVelocity(): Double {}

  override fun configPID(pidConstants: PIDConstants) {}

  override fun configPIDWrapping() {}

  override fun burnConfig() {}

  override fun factoryDefaults() {}

  override fun clearFaults() {}

  override fun getMotor(): Any {}
}

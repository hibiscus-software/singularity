/*
 * Copyright (C), 2024 Hibiscus Software and contributors. Some rights
 * reserved. This work is licensed under the terms of the MIT license
 * which can be found in the root directory of this project.
 */

package software.hibiscus.singularity.motor

import com.ctre.phoenix6.configs.MotorOutputConfigs
import com.ctre.phoenix6.configs.TalonFXConfiguration
import com.ctre.phoenix6.hardware.TalonFX
import com.ctre.phoenix6.signals.NeutralModeValue
import software.hibiscus.singularity.control.PIDConstants

class TalonFXSwerveMotor(motor: TalonFX, isDriveMotor: Boolean) : SwerveMotor() {
  override var isDriveMotor: Boolean

  private var motor: TalonFX
  private var talonFXConfig = TalonFXConfiguration()

  private var hasConfigChanged: Boolean = false

  init {
    this.isDriveMotor = isDriveMotor

    this.motor = motor

    factoryDefaults()
    clearFaults()
  }

  override fun set(percentOutput: Double) {
    motor.set(percentOutput)
  }

  override fun setVoltage(voltsOutput: Double) {
    motor.setVoltage(voltsOutput)
  }

  override fun setReference(setpoint: Double, feedforward: Double) {}

  override fun setReference(setpoint: Double, feedforward: Double, position: Double) {}

  override fun setInverted(isInverted: Boolean) {
    motor.setInverted(isInverted)
  }

  override fun setMotorBrake(isBrakeMode: Boolean) {
    if (isBrakeMode) {
      motor.setNeutralMode(NeutralModeValue.Brake)
    } else {
      motor.setNeutralMode(NeutralModeValue.Coast)
    }
  }

  override fun setVoltageCompensation(nominalVoltage: Double) {}

  override fun setCurrentLimit(currentLimit: Double) {
    talonFXConfig.CurrentLimits.SupplyCurrentLimitEnable = true
    talonFXConfig.CurrentLimits.SupplyCurrentLimit = currentLimit
    hasConfigChanged = true
  }

  override fun setLoopRampRate(rampRate: Double) {}

  override fun setEncoderPosition(position: Double) {}

  override fun getEncoderPosition(): Double {}

  override fun getEncoderVelocity(): Double {}

  override fun configPID(pidConstants: PIDConstants) {
    talonFXConfig.Slot0.kP = pidConstants.kP
    talonFXConfig.Slot0.kI = pidConstants.kI
    talonFXConfig.Slot0.kD = pidConstants.kD
    hasConfigChanged = true
  }

  override fun configPIDWrapping() {}

  override fun burnConfig() {
    if (hasConfigChanged) {
      motor.getConfigurator().apply(talonFXConfig)
      hasConfigChanged = false
    }
  }

  override fun factoryDefaults() {
    var motorOutputConfigs = MotorOutputConfigs()
  }

  override fun clearFaults() {
    motor.clearStickyFaults()
  }

  override fun getMotor(): Any {
    return this.motor
  }
}

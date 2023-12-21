/*
 * Copyright (C), 2023 Codex Microsystems. Some rights reserved. This work is
 * licensed under the terms of the MIT license which can be found in the
 * root directory of this project.
 */

package systems.codexmicro.singularity

import com.ctre.phoenix6.hardware.TalonFX
import edu.wpi.first.math.geometry.Rotation2d
import systems.codexmicro.singularity.motor.MotorType
import systems.codexmicro.singularity.motor.SwerveMotor
import systems.codexmicro.singularity.motor.TalonFXSwerveMotor

class SwerveModule(
    moduleNumber: Int,
    moduleConstants: SwerveModuleConstants,
    motorType: MotorType
) {
  private var moduleNumber: Int
  private var moduleConstants: SwerveModuleConstants

  var motorType: MotorType

  private var angleOffset: Rotation2d
  private var lastAngle: Rotation2d

  private lateinit var driveMotor: SwerveMotor
  private var angleMotor: SwerveMotor

  init {
    this.moduleNumber = moduleNumber
    this.moduleConstants = moduleConstants

    this.motorType = motorType

    this.angleOffset = moduleConstants.angleOffset

    // Drive motor config
    if (motorType == MotorType.TALON_FX) {
      driveMotor = TalonFXSwerveMotor(TalonFX(moduleConstants.driveMotorId), true)
    } else if (motorType == MotorType.SPARK_MAX) {
      driveMotor = SparkMaxSwerveMotor()
    }

    configDriveMotor()

    // Angle motor config
    configAngleMotor()
  }

  private fun configDriveMotor() {}

  private fun configAngleMotor() {}

  private fun configEncoder() {}
}

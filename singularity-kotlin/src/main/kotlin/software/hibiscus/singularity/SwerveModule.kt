/*
 * Copyright (C), 2024 Hibiscus Software and contributors. Some rights
 * reserved. This work is licensed under the terms of the MIT license
 * which can be found in the root directory of this project.
 */

package software.hibiscus.singularity

import com.ctre.phoenix6.hardware.CANcoder
import com.ctre.phoenix6.hardware.TalonFX
import edu.wpi.first.math.geometry.Rotation2d
import software.hibiscus.singularity.encoder.CANCoderSwerveEncoder
import software.hibiscus.singularity.encoder.SwerveEncoder
import software.hibiscus.singularity.encoder.SwerveEncoder.SwerveEncoderType
import software.hibiscus.singularity.motor.SparkMaxSwerveMotor
import software.hibiscus.singularity.motor.SwerveMotor
import software.hibiscus.singularity.motor.SwerveMotorType
import software.hibiscus.singularity.motor.TalonFXSwerveMotor

class SwerveModule(
    moduleNumber: Int,
    moduleConstants: SwerveModuleConstants,
    motorType: SwerveMotorType,
    encoderType: SwerveEncoderType
) {
  private var moduleNumber: Int
  private var moduleConstants: SwerveModuleConstants

  private var motorType: SwerveMotorType
  private var encoderType: SwerveEncoderType

  private var angleOffset: Rotation2d
  private var lastAngle: Rotation2d

  private lateinit var driveMotor: SwerveMotor
  private lateinit var angleMotor: SwerveMotor
  private lateinit var encoder: SwerveEncoder

  init {
    this.moduleNumber = moduleNumber
    this.moduleConstants = moduleConstants

    this.motorType = motorType
    this.encoderType = encoderType

    this.angleOffset = moduleConstants.angleOffset

    // Drive motor config
    when (motorType) {
      SwerveMotorType.TALONFX ->
          driveMotor = TalonFXSwerveMotor(TalonFX(moduleConstants.driveMotorId), true)
      SwerveMotorType.SPARKMAX -> driveMotor = SparkMaxSwerveMotor()
      else -> {
        println("[ERROR]: No Motor Type Selected for Drive Motor")
      }
    }

    configDriveMotor()

    // Angle motor config
    when (motorType) {
      SwerveMotorType.TALONFX ->
          angleMotor = TalonFXSwerveMotor(TalonFX(moduleConstants.angleMotorId), true)
      SwerveMotorType.SPARKMAX -> angleMotor = SparkMaxSwerveMotor()
      else -> {
        println("[ERROR]: No Motor Type Selected for Angle Motor")
      }
    }

    configAngleMotor()

    // Encoder config
    when (encoderType) {
      SwerveEncoderType.kCANCoder ->
          encoder = CANCoderSwerveEncoder(moduleConstants.encoderId)
      else -> {
        println("[ERROR]: No Encoder Type Selected")
      }
    }

    configEncoder()
  }

  private fun configDriveMotor() {}

  private fun configAngleMotor() {}

  private fun configEncoder() {}
}

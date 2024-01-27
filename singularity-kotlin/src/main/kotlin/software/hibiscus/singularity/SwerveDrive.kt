/*
 * Copyright (C), 2024 Hibiscus Software and contributors. Some rights
 * reserved. This work is licensed under the terms of the MIT license
 * which can be found in the root directory of this project.
 */

package software.hibiscus.singularity

import edu.wpi.first.math.geometry.Translation2d
import edu.wpi.first.math.kinematics.SwerveDriveKinematics
import edu.wpi.first.math.kinematics.SwerveDriveOdometry
import edu.wpi.first.util.sendable.Sendable
import edu.wpi.first.util.sendable.SendableBuilder
import edu.wpi.first.util.sendable.SendableRegistry
import edu.wpi.first.wpilibj.drive.RobotDriveBase
import software.hibiscus.singularity.encoder.SwerveEncoderType
import software.hibiscus.singularity.imu.NavXPortType
import software.hibiscus.singularity.imu.NavXSwerveIMU
import software.hibiscus.singularity.imu.Pigeon2SwerveIMU
import software.hibiscus.singularity.imu.SwerveIMU
import software.hibiscus.singularity.imu.SwerveIMUType
import software.hibiscus.singularity.motor.SwerveMotorType

class SwerveDrive(
    swerveConstants: SwerveDriveConstants,
    moduleConstants: Array<SwerveModuleConstants>,
    motorType: SwerveMotorType,
    encoderType: SwerveEncoderType,
    imuType: SwerveIMUType
) : RobotDriveBase(), Sendable, AutoCloseable {
  private val swerveModules: Array<SwerveModule>

  private lateinit var imu: SwerveIMU

  private val swerveKinematics: SwerveDriveKinematics
  private val swerveOdometry: SwerveDriveOdometry

  init {
    swerveModules =
        Array<SwerveModule>(4) {
          SwerveModule(0, moduleConstants[0], motorType, encoderType)
          SwerveModule(1, moduleConstants[1], motorType, encoderType)
          SwerveModule(2, moduleConstants[2], motorType, encoderType)
          SwerveModule(3, moduleConstants[3], motorType, encoderType)
        }

    // IMU config
    when (imuType) {
      SwerveIMUType.kNone -> println("[ERROR]: No IMU Type Selected for IMU")
      SwerveIMUType.kPigeon2 ->
          imu = Pigeon2SwerveIMU(swerveConstants.imuId, swerveConstants.imuCanBusName)
      SwerveIMUType.kNavX -> {
        when (swerveConstants.navXPortType) {
          NavXPortType.kNone -> println("[ERROR]: No NavX Port Type Selected for NavX Port")
          NavXPortType.kSerialPort -> imu = NavXSwerveIMU(swerveConstants.navXPort.serialPort)
          NavXPortType.kSPI -> imu = NavXSwerveIMU(swerveConstants.navXPort.spiPort)
          NavXPortType.kI2C -> imu = NavXSwerveIMU(swerveConstants.navXPort.i2cPort)
        }
      }
    }

    swerveKinematics =
        SwerveDriveKinematics(
            Translation2d(
                swerveConstants.wheelBaseMeters.divide(2.0),
                swerveConstants.trackWidthMeters.divide(2.0)
            ),
            Translation2d(
                swerveConstants.wheelBaseMeters.divide(2.0),
                swerveConstants.trackWidthMeters.divide(2.0).negate()
            ),
            Translation2d(
                swerveConstants.wheelBaseMeters.divide(2.0).negate(),
                swerveConstants.trackWidthMeters.divide(2.0)
            ),
            Translation2d(
                swerveConstants.wheelBaseMeters.divide(2.0).negate(),
                swerveConstants.trackWidthMeters.divide(2.0).negate()
            )
        )

    swerveOdometry =
        SwerveDriveOdometry(
            swerveKinematics,
        )
  }

  override fun close() {
    SendableRegistry.remove(this)
  }

  fun drive(
      translation: Translation2d,
      rotation: Double,
      isFieldRelative: Boolean,
      isOpenLoop: Boolean
  ) {}

  override fun stopMotor() {
    // TODO: Stop motors/modules
    feed()
  }

  override fun getDescription(): String {
    return "SwerveDrive"
  }

  override fun initSendable(builder: SendableBuilder) {
    builder.setSmartDashboardType("SwerveDrive")
    builder.setActuator(true)
    builder.setSafeState(this::stopMotor)
    // TODO: Add double properities
  }
}

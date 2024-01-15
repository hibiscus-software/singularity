/*
 * Copyright (C), 2023 Codex Microsystems and contributors. Some rights
 * reserved. This work is licensed under the terms of the MIT license
 * which can be found in the root directory of this project.
 */

package systems.codexmicro.singularity

import edu.wpi.first.math.geometry.Translation2d
import edu.wpi.first.math.kinematics.SwerveDriveKinematics
import edu.wpi.first.math.kinematics.SwerveDriveOdometry
import edu.wpi.first.util.sendable.Sendable
import edu.wpi.first.util.sendable.SendableBuilder
import edu.wpi.first.util.sendable.SendableRegistry
import edu.wpi.first.wpilibj.drive.RobotDriveBase
import systems.codexmicro.singularity.motor.SwerveMotorType

class SwerveDrive(
    swerveConstants: SwerveDriveConstants,
    moduleConstants: Array<SwerveModuleConstants>,
    motorType: SwerveMotorType
) : RobotDriveBase(), Sendable, AutoCloseable {
  private val swerveModules: Array<SwerveModule>

  private val swerveKinematics: SwerveDriveKinematics
  private val swerveOdometry: SwerveDriveOdometry

  init {
    swerveModules =
        Array<SwerveModule>(4) {
          SwerveModule(0, moduleConstants[0], motorType)
          SwerveModule(1, moduleConstants[1], motorType)
          SwerveModule(2, moduleConstants[2], motorType)
          SwerveModule(3, moduleConstants[3], motorType)
        }

    swerveKinematics =
        SwerveDriveKinematics(
            Translation2d(
                swerveConstants.wheelBaseMeters.divide(2.0),
                swerveConstants.trackWidthMeters.divide(2.0)
            ),
            Translation2d(
                swerveConstants.wheelBaseMeters.divide(2.0),
                -swerveConstants.trackWidthMeters.divide(2.0)
            ),
            Translation2d(
                -swerveConstants.wheelBaseMeters.divide(2.0),
                swerveConstants.trackWidthMeters.divide(2.0)
            ),
            Translation2d(
                -swerveConstants.wheelBaseMeters.divide(2.0),
                -swerveConstants.trackWidthMeters.divide(2.0)
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

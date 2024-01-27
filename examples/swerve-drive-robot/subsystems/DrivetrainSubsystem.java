package frc.robot.subsystems;

import static frc.robot.Constants.DrivetrainConstants.*;

public class DrivetrainSubsystem implements Subsystem {
  private final SwerveDrive swerveDrive = new SwerveDrive(
    kSwerveConstants, kModuleConstants, SwerveMotorType.kTalonFX,
    SwerveEncoderType.kCanCoder, SwerveIMUType.kPigeon2
  );
}

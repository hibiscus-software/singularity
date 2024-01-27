package frc.robot;

public final class Constants {
  public final class DrivetrainConstants {
    public static final int kPigeonId = 1;

    public static final Measure<Distance> kWheelBaseMeters = Meters.of(26);
    public static final Measure<Distance> kTrackWidthMeters = Meters.of(26);

    // Front left swerve module (Module 0)
    public static final int kFrontLeftDriveMotorId = 2;
    public static final int kFrontLeftAngleMotorId = 3;
    public static final int kFrontLeftEncoderId = 4;
    public static final Rotation2d kFrontLeftAngleOffset = new Rotation2d.fromDegrees(0.35);

    // Front right swerve module (Module 1)
    public static final int kFrontRightDriveMotorId = 5;
    public static final int kFrontRightAngleMotorId = 6;
    public static final int kFrontRightEncoderId = 7;
    public static final Rotation2d kFrontRightAngleOffset = new Rotation2d.fromDegrees(0.35);

    public static final SwerveDriveConstants kSwerveConstants = new SwerveDriveConstants(
      kPigeonId,
      kWheelBaseMeters,
      kTrackWidthMeters
    );

    public static final SwerveModuleConstants[] kModuleConstants = {
      new SwerveModuleConstants(
        kFrontLeftDriveMotorId,
        kFrontLeftAngleMotorId,
        kFrontLeftEncoderId,
        kFrontLeftAngleOffset
      ),
      new SwerveModuleConstants(
        kFrontRightDriveMotorId,
        kFrontRightAngleMotorId,
        kFrontRightEncoderId,
        kFrontRightAngleOffset
      ),
    };
  }
}

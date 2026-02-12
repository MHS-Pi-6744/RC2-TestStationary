// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

// ===== Not used for stationary tests - may need when implements and drivebase are merged
//import edu.wpi.first.math.geometry.Translation2d;
//import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
//import edu.wpi.first.math.trajectory.TrapezoidProfile;  
//import edu.wpi.first.math.util.Units;

/**
 * Robot-wide constants. This class should not be used for any other purpose. All constants
 * should be declared globally (i.e. public static). Do not put anything functional in this class.
 */
public final class Constants {

  // CAN IDs ========================================
  //    For the new stationary test board set up (Feb12) the SPARKmaxes will have CAN IDs 5, 6, 7, 8, 9, 10
  //    Select the CAN ID that corresponds to your test set up here:
  public static final class canIDs{
    public static final int kFeederMotorCanId = 6;    //SPARKmax - The competition robot will have 2 motors - conveyer and feeder to shooter
    public static final int kFlywheelMotorCanId = 4;  //SPARKmax - The competition robot will have 3 Sparkflex controlled motors 
    public static final int kIntakeMotorCanId = 11;   //SPARKmax -
    public static final int kPivotMotorCanId = 2;     //SPARKmax -
    public static final int climbmotor = 15;          //SPARKmax - Shouldn't this be kClimMotorCanId?  -Sr
    // Others? PHD, RoboRio?
  }

  public static final class IntakeSubsystemConstants {

    public static final class IntakeSetpoints {
      public static final double kIntake = 0.6; // Intake speed Units???
    }

    public static final class PivotSetPoints {
      public static final double kStartPosition = 0;
      public static final double kEndPosition = 90; // Degrees

      public static final int kCurrentLimit = 50;

      public static final double kZeroOffest = 0.420; //units? For stationary testbed motor

      public static final double kPositionConversionFactor = 360/16; // For stationary test bed motor
      public static final double kVelocityConversionFactor = 360/16;

      public static final IdleMode kIdleMode = IdleMode.kBrake;

      public static final double kMaxVelocity = 3072;
      public static final double kMaxAcceleration = 1536;

      // PID gains    ======== Will need to be tuned when operating on the climber     -Sr
      public static final double kP = 0.50000000;
      public static final double kI = 0.00000000;
      public static final double kD = 0.00000000;
    
      // !!!!!! These soft limits need to be checked. Are they needed for pivot? 
      //        We do need to make sure it doesn't travel too far either way  -Sr
      /** The soft limit for the elevator going forward.
      * @apiNote This soft limit should NEVER go above 24 */
      public static final double kFwdSoftLimit = 23;
      /** The soft limit for the elevator going backward.
      * @apiNote This soft limit should NEVER go below 1  */
      public static final double kRevSoftLimit = -23;

      /** The allowed tolerance for the elevator  !!!!!!!! needs fixed for Pivot  -SR
      * @apiNote This value is in inches
      * @apiNote This really shouldn't ever go above an inch.
      */
      public static final double kPositionTolerance = 0.75;
    }
  }

  // Drive constants are not used for stationary tests, and they will change for the new RC2 drive train.
  
  /*   public static final class DriveConstants {
  /*     // Maximum driving speed commands - These are the maximum speeds that can be requested by 
    // the driver or autonomous, they are not the maximum speed cababiity of the robot.
    public static final double kMaxSpeedMetersPerSecond = 3; // originally 4.8    TUNING
    public static final double kMaxAngularSpeed = 1.5*Math.PI ; // radians per second    originally 2*Pi   TUNING

    // Chassis configuration
    public static final double kTrackWidth = Units.inchesToMeters(24.5);
    // Distance between centers of right and left wheels on robot
    public static final double kWheelBase = Units.inchesToMeters(24.5);
    // Distance between front and back wheels on robot
    public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
        new Translation2d(kWheelBase / 2, kTrackWidth / 2),
        new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
        new Translation2d(-kWheelBase / 2, kTrackWidth / 2),
        new Translation2d(-kWheelBase / 2, -kTrackWidth / 2));

    // Angular offsets of the modules relative to the chassis in radians
    public static final double kFrontLeftChassisAngularOffset = -Math.PI / 2;
    public static final double kFrontRightChassisAngularOffset = 0;
    public static final double kBackLeftChassisAngularOffset = Math.PI;
    public static final double kBackRightChassisAngularOffset = Math.PI / 2;
  }

  public static final class ModuleConstants {
    / The MAXSwerve module can be configured with one of three pinion gears: 12T,
    / 13T, or 14T. This changes the drive speed of the module (a pinion gear with
    / more teeth will result in a robot that drives faster).
    
    public static final int kDrivingMotorPinionTeeth = 12;

    // Calculations required for driving motor conversion factors and feed forward
    public static final double kDrivingMotorFreeSpeedRps = NeoMotorConstants.kFreeSpeedRpm / 60;
    public static final double kWheelDiameterMeters = 0.0762;
    public static final double kWheelCircumferenceMeters = kWheelDiameterMeters * Math.PI;
    // 45 teeth on the wheel's bevel gear, 22 teeth on the first-stage spur gear, 15
    // teeth on the bevel pinion
    public static final double kDrivingMotorReduction = (45.0 * 22) / (kDrivingMotorPinionTeeth * 15);
    public static final double kDriveWheelFreeSpeedRps = (kDrivingMotorFreeSpeedRps * kWheelCircumferenceMeters)
        / kDrivingMotorReduction;
  }
  */

  // Driver interace constants
  public static final class OIConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kDriverController2Port = 1;
    public static final double kDriveDeadband = 0.05;   //  TUNING
    // An additional driver control TUNING option to try 
    // would be to square controller inputs that vary from 0 t0 1
  }
  // Not used is stationary tests - May be needed later by drive modules?
  //public static final class NeoMotorConstants {
  //  public static final double kFreeSpeedRpm = 500;
  //  public static final double kVortexKv = 565;   // rpm/V
  //}

  // Coral shooter command constants
  public static final class MotorConstants {
    public static final double k_motorSpeed = 0.4; // percent
    public static final double k_slowMotor = 0.1; // percent
    // 1:16 Ratio
    public static final double setpoint = 0;
    public static final double finalpoint = 16 ; 
  }
  // CAN ID for shooter
 public static final class ShooterSubsystemConstants {
  // SPARKmax CAN ID (Right)
   // public static final int kFlywheelFollowerMotorCanId = 16;  // SPARKmax CAN ID (Left)

    public static final class FeederSetpoints {
      public static final double kFeed = 0.15;
    }
    
    // Check these units - it looks to me like ShooterSubsystem is controlling in RPM???  Sr
    public static final class FlywheelSetpoints {
      public static final double kShootPercent = 50;
      public static final double kVelocityTolerance = 100;
    }
  }
  
public static final class ClimbSubsystemConstants {
    public static final int kPivotMotorCanId = 15;  // SPARK MAX CAN ID

    public static final class PivotSetPoints {
      public static final double kStartPosition = 0;
      public static final double kEndPosition = 90;

      public static final int kStartingPosition = 0;
      public static final int kCurrentLimit = 50;

      public static final double kZeroOffest = 0.420;

      public static final double kPositionConversionFactor = 360/16;
      public static final double kVelocityConversionFactor = 360/16;


      /** Sets the Idle mode of the motors.
      * @apiNote This should remain as {@link IdleMode#kBrake}
      * unless you want to manually rotate the motors
      */
      public static final IdleMode kIdleMode = IdleMode.kBrake;

      // Deprecated, but still nice:
      // In Desmos,
      // y=0.37037x+19.5\left\{0<\ x<27\right\}
      // y=0.6842x+11.0265\left\{27<x<65\right\}
      // is the approximate position curve for the elevator
      // where x is in Degrees of PCF1 and y is in Inches
      // Measured from the top of the shooter

      public static final double kMaxVelocity = 3072;
      public static final double kMaxAcceleration = 1536;

      public static final double kP = 0.50000000;
      public static final double kI = 0.00000000;
      public static final double kD = 0.00000000;
    
      /** The soft limit for the elevator going forward.
      * @apiNote This soft limit should NEVER go above 24
      */
      public static final double kFwdSoftLimit = 23;
      /** The soft limit for the elevator going backward.
      * @apiNote This soft limit should NEVER go below 1
      */
      public static final double kRevSoftLimit = -23;

      /** The allowed tolerance for the elevator
      * @apiNote This value is in inches
      * @apiNote This really shouldn't ever go above an inch.
      */
      public static final double kPositionTolerance = 0.75;

      public static final double kStageLoad = 7.86;
      public static final double kStageL1 = 6.61;
      public static final double kStageL2 = 13.65;
      public static final double kStageL3 = 22;
      public static final double kStageAlgae = 6.61;
    }
  }
  
}
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

/**
 * Robot-wide constants. This class should not be used for any other purpose. All constants
 * should be declared globally (i.e. public static). Do not put anything functional in this class.
 */
public final class Constants {
  public static final class OIConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kDriverController2Port = 1;
    public static final double kDriveDeadband = 0.05;   //  TUNING
    // An additional driver control TUNING option to try 
    // would be to square controller inputs that vary from 0 t0 1
  }

  public static final class NeoMotorConstants {
    public static final double kFreeSpeedRpm = 500;
    public static final double kVortexKv = 565;   // rpm/V
  }

  // Coral shooter command constants
  public static final class MotorConstants {
    public static final double k_motorSpeed = 0.6; // percent
    public static final double k_slowMotor = 0.1; // percent
  }
  // CAN ID for shooter
 public static final class ShooterSubsystemConstants {
    public static final int kFeederMotorCanId = 6;    // SPARKmax CAN ID
    public static final int kFlywheelMotorCanId = 4;  // SPARKmax CAN ID (Right)
   // public static final int kFlywheelFollowerMotorCanId = 16;  // SPARKmax CAN ID (Left)

    public static final class FeederSetpoints {
      public static final double kFeed = 0.15;
    }
    
    public static final class FlywheelSetpoints {
//This is a percentage variable🙏 don't be hamza
//Hamza here, the message above is correct
      public static final double kShootPercent = 100;
      public static final double kVelocityTolerance = 100;
    }
  }
  
public static final class ClimbSubsystemConstants {
    public static final int kPivotMotorCanId = 2;  // SPARK MAX CAN ID

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
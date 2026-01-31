// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot; 
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


  
}

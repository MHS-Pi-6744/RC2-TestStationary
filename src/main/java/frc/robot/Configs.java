package frc.robot;

import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.config.MAXMotionConfig.MAXMotionPositionMode;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.FeedbackSensor;

import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.MAXMotionConfig.MAXMotionPositionMode;
import frc.robot.Constants.IntakeSubsystemConstants.PivotSetPoints;

import frc.robot.Constants.IntakeSubsystemConstants.PivotSetPoints;

//import frc.robot.Constants.ModuleConstants; Doesn't do anything

public final class Configs {
private static final  double nominalVoltage = 12.0;


    public static final class Motor {
        public static final SparkMaxConfig defaultConfig = new SparkMaxConfig();

        static {
            // Use module constants to calculate conversion factors and feed forward gain.
            defaultConfig
                .idleMode(IdleMode.kBrake)
                .smartCurrentLimit(50);
        }
    }

    public static final class IntakeSubsystem {
        public static final SparkMaxConfig intakeConfig = new SparkMaxConfig();
        public static final SparkMaxConfig pivotConfig = new SparkMaxConfig();

        static {
            // Configure basic settings of the intake
            intakeConfig
                .inverted(true)
                .idleMode(IdleMode.kCoast)
                .openLoopRampRate(0.5)
                .smartCurrentLimit(40);
                     
            pivotConfig
                .idleMode(PivotSetPoints.kIdleMode)
                .smartCurrentLimit(PivotSetPoints.kCurrentLimit)
                .inverted(false);
            pivotConfig.absoluteEncoder
                .inverted(false)
                .zeroOffset(PivotSetPoints.kZeroOffest)
                .zeroCentered(false)
                .positionConversionFactor(360)
                .velocityConversionFactor(360);
            pivotConfig.encoder
            .positionConversionFactor(PivotSetPoints.kPositionConversionFactor)
            .velocityConversionFactor(PivotSetPoints.kVelocityConversionFactor);
            pivotConfig.closedLoop
                .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
                .pid(PivotSetPoints.kP, PivotSetPoints.kI, PivotSetPoints.kD)
                .outputRange(-1, 1)
                .maxMotion    
                .cruiseVelocity(PivotSetPoints.kMaxVelocity)
                .maxAcceleration(PivotSetPoints.kMaxAcceleration)
                .positionMode(MAXMotionPositionMode.kMAXMotionTrapezoidal)
                .allowedProfileError(PivotSetPoints.kPositionTolerance);
            pivotConfig.softLimit
                .forwardSoftLimit(PivotSetPoints.kFwdSoftLimit)
                .reverseSoftLimit(PivotSetPoints.kRevSoftLimit)
                .reverseSoftLimitEnabled(true)
                .forwardSoftLimitEnabled(true);
        }
    }
public static final class ShooterSubsystem {
    public static final SparkFlexConfig flywheelConfig = new SparkFlexConfig();
    public static final SparkFlexConfig flywheelFollowerConfig = new SparkFlexConfig();
    public static final SparkFlexConfig feederConfig = new SparkFlexConfig();

    static {
      // Configure basic setting of the flywheel motors


      /*
       * Configure the closed loop controller. We want to make sure we set the
       * feedback sensor as the primary encoder.
       */
      // Configure basic setting of the feeder motor
      feederConfig
        .inverted(false)
        .idleMode(IdleMode.kCoast)
        .openLoopRampRate(1.0)
        .smartCurrentLimit(60);

        
    }
  }

   public static final class ClimberSubsystem {
        public static final SparkMaxConfig intakeConfig = new SparkMaxConfig();
        public static final SparkMaxConfig pivotConfig = new SparkMaxConfig();

        static {
            // Configure basic settings of the intake
            intakeConfig
                .inverted(false)
                .idleMode(IdleMode.kCoast)
                .openLoopRampRate(0.5)
                .smartCurrentLimit(40);
            
            pivotConfig
                .idleMode(PivotSetPoints.kIdleMode)
                .smartCurrentLimit(PivotSetPoints.kCurrentLimit)
                .inverted(false);
            pivotConfig.absoluteEncoder
                .inverted(false)
                .positionConversionFactor(360)
                .velocityConversionFactor(360)
                .zeroOffset(PivotSetPoints.kZeroOffest)
                .zeroCentered(false);
            pivotConfig.encoder
            .positionConversionFactor(PivotSetPoints.kPositionConversionFactor)
            .velocityConversionFactor(PivotSetPoints.kVelocityConversionFactor);
            pivotConfig.closedLoop
                .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
                .pid(PivotSetPoints.kP, PivotSetPoints.kI, PivotSetPoints.kD)
                .outputRange(-1, 1)
                .maxMotion    
                .cruiseVelocity(PivotSetPoints.kMaxVelocity)
                .maxAcceleration(PivotSetPoints.kMaxAcceleration)
                .positionMode(MAXMotionPositionMode.kMAXMotionTrapezoidal)
                .allowedProfileError(PivotSetPoints.kPositionTolerance);
            pivotConfig.softLimit
                .forwardSoftLimit(PivotSetPoints.kFwdSoftLimit)
                .reverseSoftLimit(PivotSetPoints.kRevSoftLimit)
                .reverseSoftLimitEnabled(true)
                .forwardSoftLimitEnabled(true);
        }
    }
}

package frc.robot;

import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.MAXMotionConfig.MAXMotionPositionMode;
import frc.robot.Constants.IntakeSubsystemConstants.PivotSetPoints;

public final class Configs {
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
                .zeroOffset(PivotSetPoints.kZeroOffest)
                .zeroCentered(true);
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

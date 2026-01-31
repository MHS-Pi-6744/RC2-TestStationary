package frc.robot;

import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

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
                .inverted(false)
                .idleMode(IdleMode.kCoast)
                .openLoopRampRate(0.5)
                .smartCurrentLimit(40);
        }
    }
}

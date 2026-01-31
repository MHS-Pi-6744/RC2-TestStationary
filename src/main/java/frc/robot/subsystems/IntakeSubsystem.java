package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.IntakeSubsystemConstants;
import frc.robot.Constants.IntakeSubsystemConstants.PivotSetPoints;
import frc.robot.Constants.IntakeSubsystemConstants.IntakeSetpoints;
import frc.robot.Configs;

public class IntakeSubsystem extends SubsystemBase {
    // Initialize intake Spark. We will use open loop control for this
    private SparkMax intakeMotor =
        new SparkMax(IntakeSubsystemConstants.kIntakeMotorCanId, MotorType.kBrushless);

    private SparkMax pivotMotor =
        new SparkMax(IntakeSubsystemConstants.kPivotMotorCanId, MotorType.kBrushless);

    public IntakeSubsystem() {
        /*
        * Apply the appropriate configurations to the SPARKs.
        *
        * kResetSafeParameters is used to get the SPARK to a known state. This
        * is useful in case the SPARK is replaced.
        *
        * kPersistParameters is used to ensure the configuration is not lost when
        * the SPARK loses power. This is useful for power cycles that may occur
        * mid-operation.
        */
        intakeMotor.configure(
            Configs.IntakeSubsystem.intakeConfig,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);
        
        pivotMotor.configure(
            Configs.IntakeSubsystem.pivotConfig,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);
        
        System.out.println("---> IntakeSubsystem initialized");
    }

    /** Set the intake motor power in the range of [-1, 1]. 
     * @author Pubert
    */
    private void setIntakePower(double power) {
        intakeMotor.set(power);
    }

    /** Set the conveyor motor power in the range of [-1, 1]. 
     * @author Pubert
    */
    private void setPivotPower(double power) {
        pivotMotor.set(power);
    }

    /**
     * {@link Command} to run the intake motor power {@link Command}. When the
     * {@link Command} is interrupted, e.g. the button is released,
     * the motors will stop
     * 
     * @author Pubert
     */
    public Command runIntakeCommand() {
        return this.startEnd(
            () -> {
                this.setIntakePower(IntakeSetpoints.kIntake);
            }, () -> {
                this.setIntakePower(0);
        }).withName("Intaking");
    }

    /**
     * {@link Command} to move the Pivot Motor forward.
     * 
     * @author Pubert
     */
    public Command runForwardPivot() {
        return this.startEnd(
            () -> {
                this.setPivotPower(PivotSetPoints.kIntake);
            }, () -> {
                this.setPivotPower(0.0);
        }).withName("Moving Pivot Forward");
    }

    /**
     * {@link Command} to move the Pivot Motor backward.
     * 
     * @author Pubert
     */
    public Command runBackwardPivot() {
        return this.startEnd(
            () -> {
                this.setPivotPower(PivotSetPoints.kExtake);
            }, () -> {
                this.setPivotPower(0.0);
            }).withName("Moving Pivot Backward");
    }

    @Override
    public void periodic() {
        // Display subsystem values
        SmartDashboard.putNumber("Intake | Intake | Applied Output", intakeMotor.getAppliedOutput());
        SmartDashboard.putNumber("Pivot | Pivot | Applied Output", pivotMotor.getAppliedOutput());
    }
}

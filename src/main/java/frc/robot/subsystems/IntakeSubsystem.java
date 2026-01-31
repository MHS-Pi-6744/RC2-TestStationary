package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.IntakeSubsystemConstants;
import frc.robot.Constants.IntakeSubsystemConstants.ConveyorSetpoints;
import frc.robot.Constants.IntakeSubsystemConstants.IntakeSetpoints;
import frc.robot.Configs;

public class IntakeSubsystem extends SubsystemBase {
    // Initialize intake Spark. We will use open loop control for this
    private SparkMax intakeMotor =
        new SparkMax(IntakeSubsystemConstants.kIntakeMotorCanId, MotorType.kBrushless);

    private SparkMax conveyorMotor =
        new SparkMax(IntakeSubsystemConstants.kConveyorMotorCanId, MotorType.kBrushless);

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
        
        conveyorMotor.configure(
            Configs.IntakeSubsystem.conveyorConfig,
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
    private void setConveyorPower(double power) {
        conveyorMotor.set(power);
    }

    /**
     * {@link Command} to run the intake and conveyor motors. When the
     * {@link Command} is interrupted, e.g. the button is released,
     * the motors will stop
     * 
     * @author Pubert
     */
    public Command runIntakeCommand() {
        return this.startEnd(
            () -> {
                this.setIntakePower(IntakeSetpoints.kIntake);
                this.setConveyorPower(ConveyorSetpoints.kIntake);
            }, () -> {
                this.setIntakePower(0);
                this.setConveyorPower(0);
        }).withName("Intaking");
    }

    /**
     * {@link Command} to reverse the intake motor and conveyor
     * motors. When the {@link Command} is interrupted, e.g. the
     * button is released, the motors will stop.
     * 
     * @author Pubert
     */
    public Command runExtakeCommand() {
        return this.startEnd(
            () -> {
                this.setIntakePower(IntakeSetpoints.kExtake);
                this.setConveyorPower(ConveyorSetpoints.kExtake);
            }, () -> {
                this.setIntakePower(0.0);
                this.setConveyorPower(0.0);
        }).withName("Extaking");
    }

    @Override
    public void periodic() {
        // Display subsystem values
        SmartDashboard.putNumber("Intake | Intake | Applied Output", intakeMotor.getAppliedOutput());
        SmartDashboard.putNumber("Conveyor | Conveyor | Applied Output", conveyorMotor.getAppliedOutput());
    }
}

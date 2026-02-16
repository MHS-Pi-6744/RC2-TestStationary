package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.NeoVortexConstants;

public class Flywheel extends SubsystemBase {
    // The shooter motor
    private SparkMax m_otor;
    private RelativeEncoder e_ncoder;
    private SparkClosedLoopController m_pidController;
    private double kP, kI, kD, kIz, kMaxOutput, kMinOutput, percSet;

    private String s_motorName;

    // DriveSubsystem constructor - creates & initializes DriveSubsystem object
    public Flywheel(int canID, SparkMaxConfig config) {
        percSet = 0;
        s_motorName = "Flywheel #" + canID;
        m_otor = new SparkMax(canID, MotorType.kBrushless);
        m_pidController = m_otor.getClosedLoopController();
        e_ncoder = m_otor.getEncoder();

        kP = 0.00009;
        kI = 0.0000001;
        kD = 0.0005;
        kIz = 0;
        kMaxOutput = 1;
        kMinOutput = -1;

        config.closedLoop
                .p(kP)
                .i(kI)
                .d(kD)
                .minOutput(kMinOutput)
                .maxOutput(kMaxOutput)
                .iZone(kIz);

        // Apply the configuration settings to the shooter motor SPARK MAX
        // - kResetSafeParameters is used to get the SPARK MAX to a known state. This
        // is useful in case the SPARK MAX is replaced.
        // - kPersistParameters is used to ensure the configuration is not lost when
        // the SPARK MAX loses power. This is useful for power cycles that may occur
        // mid-operation.

        m_otor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        e_ncoder.setPosition(0);
    }

    
    private void rpmCtl(double rpm) {
        m_pidController.setSetpoint(rpm, ControlType.kVelocity);
    }
    
    public void percentCtl(double percent) {
        rpmCtl((percent / 100) * NeoVortexConstants.kMaxRPM);
    }
    
    public Command stopMotor() {
        return run(
                () -> rpmCtl(0)
                );
    }

    public Command runPercent(double percent) {
        return run(
                () -> percentCtl(percent));
    }
    
    public Command runRPM(double rpm) {
        return run(
                () -> rpmCtl(rpm));
    }

    public Command runAtSet() {
        return run(
                () -> percentCtl(percSet));
    }

    private void addToSet(double inc) {
        percSet += inc;
    }

    public Command incrSet(double inc) {
        return new InstantCommand(
            () -> addToSet(inc)
        );
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        // m_shooterMotor.getOutputCurrent();
        SmartDashboard.putNumber(s_motorName + " Output", m_otor.getAppliedOutput());
        SmartDashboard.putNumber(s_motorName + " Current", m_otor.getOutputCurrent());

        SmartDashboard.putNumber(s_motorName + " Position", e_ncoder.getPosition());
        SmartDashboard.putNumber(s_motorName + " Velocity", e_ncoder.getVelocity());

        SmartDashboard.putBoolean(s_motorName + " At Setpoint", m_pidController.isAtSetpoint());
        SmartDashboard.putNumber(s_motorName + " Setpoint", m_pidController.getSetpoint());
        
        SmartDashboard.putNumber(s_motorName + " Internal Perc Setpoint", percSet);
    }
}
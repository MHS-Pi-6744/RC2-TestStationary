package frc.robot.subsystems;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorConstants;

public class MotorController extends SubsystemBase{
    // The shooter motor
    private SparkMax m_otor;
    private RelativeEncoder e_ncoder;
    private SparkClosedLoopController p_motor;

    private double m_setpoint;

    private String s_motorName;

    // DriveSubsystem constructor - creates & initializes DriveSubsystem object
    public MotorController(int canID, SparkMaxConfig config){
        s_motorName = "Motor #" + canID;
        m_otor = new SparkMax(canID, MotorType.kBrushless);
        e_ncoder = m_otor.getEncoder(); 
        p_motor = m_otor.getClosedLoopController();

        // Apply the configuration settings to the shooter motor SPARK MAX   
        // - kResetSafeParameters is used to get the SPARK MAX to a known state. This
        //     is useful in case the SPARK MAX is replaced.
        // - kPersistParameters is used to ensure the configuration is not lost when
        //     the SPARK MAX loses power. This is useful for power cycles that may occur
        //     mid-operation.
     
        m_otor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        m_setpoint = 0;
        e_ncoder.setPosition(0);
    }

    //Shooter Commands
    public boolean atTargetPosition() {
        return Math.abs(avgEncodePos() - m_setpoint) < 1;
    }
    public double avgEncodePos() {
        return (e_ncoder.getPosition());
    }
    public void setTargetPosition(double setpoint) {
        m_setpoint = setpoint;
        moveToSetpoint();
    }
    public void moveToSetpoint() {
        p_motor.setReference(m_setpoint, ControlType.kMAXMotionPositionControl);
    }
    public Command stopMotor(){
        return run(
        () -> m_otor.set(0));
    }

    public Command resetPos(){
        return run(
            () -> e_ncoder.setPosition(0));
    }

    public Command setPos(double position){
        return run(
            () -> setTargetPosition(position));
    }

    /**
     * @return a {@link Command} that sets the motor speed to {@link MotorConstants#k_motorSpeed}
     */
    public Command runForward() {
        return run(
            () -> m_otor.set(MotorConstants.k_motorSpeed)
        );
    }    
    
    /**
     * @return a {@link Command} that sets the motor speed to -{@link MotorConstants#k_motorSpeed}
     */
    public Command runReverse() {
        return run(
            () -> m_otor.set(-MotorConstants.k_motorSpeed)
        );
    }    

    /**
     * @return a {@link Command} that sets the motor speed to {@link MotorConstants#k_motorSpeed}
     */
    public Command walkForward() {
        return run(
            () -> m_otor.set(MotorConstants.k_slowMotor)
        );
    }    
    
    /**
     * @return a {@link Command} that sets the motor speed to -{@link MotorConstants#k_slowMotor}
     */
    public Command walkReverse() {
        return run(
            () -> m_otor.set(-MotorConstants.k_slowMotor)
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
    }
}
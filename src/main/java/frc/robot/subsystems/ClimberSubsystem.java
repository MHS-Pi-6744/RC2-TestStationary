package frc.robot.subsystems;


import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkAbsoluteEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs;
//import frc.robot.Constants.ElevatorConstants;
import frc.robot.Constants.climbConstants;
import frc.robot.Constants.ShooterSubsystemConstants;

public class ClimberSubsystem extends SubsystemBase {
  private SparkMax m_otor;

  private SparkClosedLoopController p_motor;

  private SparkAbsoluteEncoder e_cal;

  private RelativeEncoder e_ncoder;

  private SparkMaxConfig c_shepherd;

  private double m_setpoint;

  private String s_motorName;

  public boolean ran;

   private SparkMax m_climbMotor =
      new SparkMax(ShooterSubsystemConstants.kFeederMotorCanId, MotorType.kBrushless);

  public ClimberSubsystem() {


    //c_shepherd = Configs.ElevatorSubsystem.shepherdConfig;

   // m_otor.configure(c_shepherd, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    
    m_setpoint = climbConstants.setpoint;

    p_motor = m_climbMotor.getClosedLoopController();

    e_ncoder = m_climbMotor.getEncoder();

    e_cal = m_climbMotor.getAbsoluteEncoder();

    //e_ncoder.setPosition(0);

    e_ncoder.setPosition(e_cal.getPosition());

  }

  public boolean atTargetPosition() {
    return Math.abs(avgEncoderPos() - m_setpoint) < 1;
  }

  public void setthepos() {
    while(e_ncoder.getPosition() < climbConstants.finalpoint-5){
      m_otor.set(climbConstants.k_motorSpeed);
    }
    m_otor.set(0);
  }

  public void resetthepos() {
    while(e_ncoder.getPosition() > climbConstants.setpoint+5){
      m_otor.set(-climbConstants.k_motorSpeed);
    }
    m_otor.set(0);
    }

  public double avgEncoderPos() {
    return (e_ncoder.getPosition() / 2);
  }

  public void setTargetPosition(double setpoint) {
    m_setpoint = setpoint;
    moveToSetpoint();
  }

  private void moveToSetpoint() {
    p_motor.setReference(m_setpoint, ControlType.kMAXMotionPositionControl);
  }

  public void stickControl(double stick) {
    m_otor.set(stick);
  }

  public Command resetClimber() {
    return run(
      () -> resetthepos());
  }

  public Command slowBottom() {
    return startEnd(
      () -> m_otor.set(-0.1),
      () -> m_otor.set(0)
      );
  }

  public Command setClimber() {
    return run(
      () -> setthepos());
  }
  public void setArmCoastMode(){
    SparkMaxConfig c_mod = new SparkMaxConfig();
    c_mod.idleMode(IdleMode.kCoast);
    m_otor.configure(c_mod, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
  }

  public void setArmBrakeMode(){
    SparkMaxConfig c_mod = new SparkMaxConfig();
    c_mod.idleMode(IdleMode.kBrake);
    m_otor.configure(c_mod, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
  }
  
  @Override
  public void periodic() { // This method will be called once per scheduler run
    SmartDashboard.putNumber("Calibrator Position", e_cal.getPosition());
    SmartDashboard.putNumber("Calibrator Velocity", e_cal.getVelocity());
    SmartDashboard.putNumber("Shepherd Position", e_ncoder.getPosition());
    SmartDashboard.putNumber("Shepherd Velocity", e_ncoder.getVelocity());
    SmartDashboard.putNumber("Setpoint", m_setpoint);
    SmartDashboard.putBoolean("At Target", atTargetPosition());
    SmartDashboard.putNumber(s_motorName + " Output", m_otor.getAppliedOutput());
    SmartDashboard.putNumber(s_motorName + " Current", m_otor.getOutputCurrent());
    SmartDashboard.putNumber(s_motorName + " Position", e_ncoder.getPosition());
    SmartDashboard.putNumber(s_motorName + " Velocity", e_ncoder.getVelocity());
  }
}
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.NamedCommands;

//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
//  import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Configs.Motor;
import frc.robot.Constants.OIConstants;
import frc.robot.BuildConstants;
import frc.robot.subsystems.MotorController;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.SystemSelect;
import frc.robot.SystemSelect;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */


@SuppressWarnings("unused")
public class RobotContainer {
  //MotorController m_motor1 = new MotorController(6, Motor.defaultConfig);


  
  private FeederSubsystem m_feeder = new FeederSubsystem();
    private ShooterSubsystem m_shooter = new ShooterSubsystem();
        private ClimberSubsystem m_climbMotor = new ClimberSubsystem();
        IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
      
        public void updateshuffleboard(){
          SmartDashboard.updateValues();
        }
      
        private final Trigger isFlywheelSpinning = new Trigger(m_shooter.isFlywheelSpinning);
        // The driver's controller
        CommandXboxController m_controller1 = new CommandXboxController(OIConstants.kDriverControllerPort);
        // TODO: Make Guitar Hero Guitar work somehow
      
      
        //m_chooser
        SendableChooser<Command> m_chooser = new SendableChooser<>();
      
        /**
         * The container for the robot. Contains subsystems, OI devices, and commands.
         */
        public RobotContainer() {
        /*  NamedCommands.registerCommand( "Run Forward", m_motor1. runForward());
          NamedCommands.registerCommand( "Run Reverse", m_motor1. runReverse());
          NamedCommands.registerCommand("Walk Forward", m_motor1.walkForward());
          NamedCommands.registerCommand("Walk Reverse", m_motor1.walkReverse());*/ 
      
          //m_chooser
    if(SystemSelect.isFeeder){
        m_feeder = null;
    }
    
    if(SystemSelect.isShooter){
        m_shooter = null;
    }

    if(SystemSelect.isClimber){
        m_climbMotor = null;
    }

    if(SystemSelect.isIntake){
        intakeSubsystem = null;
    }

    m_chooser.addOption("Do Nothing", new Command(){});
    SmartDashboard.putData("Auto Chooser", m_chooser);

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its
   * subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then calling
   * passing it to a
   * {@link JoystickButton}.
   */
  private void configureButtonBindings() {

if(SystemSelect.isIntake){
if(SystemSelect.isIntake){
    m_controller1.a().whileTrue(
      intakeSubsystem.runIntakeCommand());

    m_controller1.povUp().whileTrue(
      intakeSubsystem.runForwardPivot());

    m_controller1.povDown().whileTrue(
      intakeSubsystem.runBackwardPivot());
}

if(SystemSelect.isFeeder){
  m_controller1.x().toggleOnTrue(m_feeder.runFeederCommand().onlyWhile(isFlywheelSpinning)); 
}

if(SystemSelect.isClimber){
    m_controller1.leftBumper().toggleOnTrue(m_climbMotor.runBackwardPivot());
    m_controller1.rightBumper().toggleOnTrue(m_climbMotor.runForwardPivot());
}

if(SystemSelect.isShooter){
}

if(SystemSelect.isFeeder){
  m_controller1.x().toggleOnTrue(m_feeder.runFeederCommand().onlyWhile(isFlywheelSpinning)); 
}

if(SystemSelect.isClimber){
    m_controller1.leftBumper().toggleOnTrue(m_climbMotor.runBackwardPivot());
    m_controller1.rightBumper().toggleOnTrue(m_climbMotor.runForwardPivot());
}

if(SystemSelect.isShooter){
    m_controller1.leftTrigger().whileTrue(m_shooter.slowDownCommand());
    m_controller1.rightTrigger().whileTrue(m_shooter.speedUpCommand());
    m_controller1.y().toggleOnTrue(m_shooter.runShooterCommand());
}

}

  }

  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }


  public void printGitData() {
    System.out.println("Repo:" + BuildConstants.MAVEN_NAME);
    System.out.println("Branch:" + BuildConstants.GIT_BRANCH);
    System.out.println("Git Date:" + BuildConstants.GIT_DATE);
    System.out.println("Build Date:" + BuildConstants.BUILD_DATE);
  };


 
  


  
}

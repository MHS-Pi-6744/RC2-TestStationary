// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.BooleanSupplier;

import com.pathplanner.lib.auto.NamedCommands;

//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RepeatCommand;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
//  import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Configs.Motor;
import frc.robot.Constants.OIConstants;
import frc.robot.BuildConstants;
import frc.robot.subsystems.FlywheelV1;
import frc.robot.subsystems.FlywheelV2;
import frc.robot.subsystems.MotorController;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.SystemSelect;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */


@SuppressWarnings("unused")
public class RobotContainer {
  MotorController feeder = new MotorController(16, Motor.defaultConfig);
  // FlywheelV1 m1 = new FlywheelV1(18, Motor.defaultConfig, 17, 19);
  FlywheelV2 f_left = new FlywheelV2(17, Motor.defaultConfig);
  FlywheelV2 f_center = new FlywheelV2(18, Motor.defaultConfig);
  FlywheelV2 f_right = new FlywheelV2(19, Motor.defaultConfig.inverted(true));

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  public void updateshuffleboard(){
    SmartDashboard.updateValues();
  }

 
  // The driver's controller
  CommandXboxController m_controller1 = new CommandXboxController(OIConstants.kDriverControllerPort);
  CommandGenericHID m_controller2 = new CommandGenericHID(OIConstants.kDriverController2Port);
  // TODO: Make Guitar Hero Guitar work somehow
  // CommandGenericHID m_guitar = new CommandGenericHID(3);

    m_chooser.addOption("Do Nothing", new Command(){});
    SmartDashboard.putData("Auto Chooser", m_chooser);

    if(SystemSelect.isFeeder){
      m_feeder = new FeederSubsystem();
    }

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
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

    m_controller1.povUp()
      .toggleOnTrue(
        new ParallelCommandGroup(
          f_left.incrSet(500),
          f_center.incrSet(500),
          f_right.incrSet(500)
        )
      );
    m_controller1.povDown()
      .toggleOnTrue(
        new ParallelCommandGroup(
          f_left.incrSet(-500),
          f_center.incrSet(-500),
          f_right.incrSet(-500)
        )
      );
    m_controller1.rightBumper()
      .onTrue(
        new ParallelCommandGroup(
          f_left.runAtSet(),
          f_center.runAtSet(),
          f_right.runAtSet()
        )
      )
      .onFalse(
        new ParallelCommandGroup(
          f_left.stopMotor(),
          f_center.stopMotor(),
          f_right.stopMotor()
        )
      );
    m_controller1.x()
      .toggleOnTrue(feeder.runForward())
      .toggleOnFalse(feeder.stopMotor());
  }

  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }

  public void PrintData() {
    System.out.println("Repo:" + BuildConstants.MAVEN_NAME);
    System.out.println("Branch:" + BuildConstants.GIT_BRANCH);
    System.out.println("Git Date:" + BuildConstants.GIT_DATE);
    System.out.println("Build Date:" + BuildConstants.BUILD_DATE);

    System.out.println("Climber Enabled: " + SystemSelect.isClimber);
    System.out.println("Feeder Enabled: " + SystemSelect.isFeeder);
    System.out.println("Intake Enabled: " + SystemSelect.isIntake);
    System.out.println("Shooter Enabled: " + SystemSelect.isShooter);
  };


 
  


  
}

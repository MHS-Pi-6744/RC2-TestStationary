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
import frc.robot.Configs.Motor;
import frc.robot.Constants.OIConstants;
import frc.robot.BuildConstants;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.MotorController;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */


@SuppressWarnings("unused")
public class RobotContainer {
  Flywheel m_flywheel = new Flywheel(8, Motor.defaultConfig);

  public void updateshuffleboard(){
    SmartDashboard.updateValues();
  }

 
  // The driver's controller
  CommandXboxController m_controller1 = new CommandXboxController(OIConstants.kDriverControllerPort);
  CommandGenericHID m_controller2 = new CommandGenericHID(OIConstants.kDriverController2Port);
  // TODO: Make Guitar Hero Guitar work somehow
  // CommandGenericHID m_guitar = new CommandGenericHID(3);

  //m_chooser
  SendableChooser<Command> m_chooser = new SendableChooser<>();


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

    m_controller1.a()
      .onTrue(m_flywheel.runPercent(0.35))
      .onFalse(m_flywheel.stopMotor());

    m_controller1.rightBumper()
      .onTrue(m_flywheel.runAtSet())
      .onFalse(m_flywheel.stopMotor());

    m_controller1.povUp()
      .toggleOnTrue(m_flywheel.incrSet(0.05));
    m_controller1.povDown()
      .toggleOnTrue(m_flywheel.incrSet(-0.05));
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

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.ShooterRevCommand;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  private final DriveTrainSubsystem driveTrainSubsystem = new DriveTrainSubsystem();
private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  XboxController m_driverController = new XboxController(Constants.CONTROLLER);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    driveTrainSubsystem.setDefaultCommand(
      new RunCommand(() -> driveTrainSubsystem.arcadeDrive(getControllerLeftX(), getControllerRightY(), getControllerRightX()), driveTrainSubsystem)
    );
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    new JoystickButton(m_driverController, Button.kB.value)
    .whileActiveOnce(new ShooterRevCommand(shooterSubsystem));

  }

// Tests for the deadzone

  private double deadZoneMod(double val) {
		if (Math.abs(val) <= Constants.JOYSTICK_DEADZONE) {
			return 0;
		} else {
			return val;
		}
  }

  // Looks for the X/Y for right strafing and X for left rotation
  
  public double getControllerRightX() {
		if ( m_driverController != null ) {
			return deadZoneMod( m_driverController.getX( Hand.kRight ) );
		} else {
			return 0;
		}
  }
  
  public double getControllerRightY() {
		if ( m_driverController != null ) {
			return deadZoneMod( m_driverController.getY( Hand.kRight ) );
		} else {
			return 0;
		}
  }
  
  public double getControllerLeftX() {
		if ( m_driverController != null ) {
			return deadZoneMod( m_driverController.getX( Hand.kLeft ) );
		} else {
			return 0;
		}
  }




  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}

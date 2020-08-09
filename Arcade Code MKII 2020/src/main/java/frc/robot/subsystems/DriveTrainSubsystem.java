/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrainSubsystem extends SubsystemBase {
  /**
   * Creates a new DriveTrainSubsystem.
   */
  private CANSparkMax frontLeft, backLeft, frontRight, backRight;
  
  public DriveTrainSubsystem() {

    frontLeft = new CANSparkMax(Constants.FRONT_LEFT_SPARK, MotorType.kBrushed);
    backLeft = new CANSparkMax(Constants.BACK_LEFT_SPARK, MotorType.kBrushed);
    frontRight = new CANSparkMax(Constants.FRONT_RIGHT_SPARK, MotorType.kBrushed);
    backRight = new CANSparkMax(Constants.BACK_RIGHT_SPARK, MotorType.kBrushed);
  
    frontLeft.setInverted(false);
    backLeft.setInverted(false);
    frontRight.setInverted(false);
    backRight.setInverted(false);

  }

  public void moveLeftTrain( double speed ) {

    frontLeft.set( speed );
    backLeft.set( speed );

  }
  
  public void moveRightTrain( double speed ) {

    frontRight.set( speed );
    backRight.set( speed );

  }

  public void arcadeDrive( double X, double Y, double R ) {

    moveLeftTrain(-Y - X);
    moveRightTrain(-Y + X);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {

  private Spark shooterSparkA, shooterSparkB;
  /**
   * Creates a new ShooterSubsystem.
   */
  public ShooterSubsystem() {

    shooterSparkA = new Spark(Constants.SHOOTER_SPARK_A);
    shooterSparkB = new Spark(Constants.SHOOTER_SPARK_B);

    shooterSparkA.setInverted(false);
    shooterSparkB.setInverted(false);

  }

  public void rev() {

    shooterSparkA.set(Constants.SHOOTER_SPEED);
    shooterSparkB.set(-Constants.SHOOTER_SPEED);

  }

  public void revStop() {

    shooterSparkA.set(0);
    shooterSparkB.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

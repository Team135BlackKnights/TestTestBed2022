// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAlternateEncoder;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.DutyCycle;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static Joystick leftJoystick = new Joystick(0);
  CANSparkMax leftLauncher = new CANSparkMax(1, com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless);
  CANSparkMax rightLauncher = new CANSparkMax(4, com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless);

  Encoder shootEncoder;
  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    shootEncoder = new Encoder(1, 0, false, Encoder.EncodingType.k4X);
    shootEncoder.setDistancePerPulse(.00048828125);

    SmartDashboard.putNumber("DPP", shootEncoder.getDistancePerPulse());
    leftLauncher.setIdleMode(IdleMode.kCoast);
    rightLauncher.setIdleMode(IdleMode.kCoast);
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() 
  {
   SmartDashboard.putNumber("RPM G", shootEncoder.getRate() * 60);
   SmartDashboard.putNumber("RPM", shootEncoder.getRate() * 60);
   leftLauncher.set(leftJoystick.getRawAxis(3));
   rightLauncher.set(-leftJoystick.getRawAxis(3));
   SmartDashboard.putNumber("Joysticks", leftJoystick.getRawAxis(3));
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}

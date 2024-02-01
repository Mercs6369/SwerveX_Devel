// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  private final SendableChooser<String>  m_autoRoutine = new SendableChooser<>();
  private static final String kRoutine1 = "Option1";
  private static final String kRoutine2 = "Option2";
  private static final String kRoutine3 = "Option3";
  private static final String kRoutine4 = "Option4";
  private static final String kRoutine5 = "Option5";
  private String m_autoRoutineSelected;
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  Drivetrain m_Drivetrain = new Drivetrain();
  Joystick driver = new Joystick(0);



  @Override
  public void robotInit() {
    m_autoRoutine.setDefaultOption("Option1", kRoutine1);
    m_autoRoutine.addOption("Option2", kRoutine2);
    m_autoRoutine.addOption("Option3", kRoutine3);
    m_autoRoutine.addOption("Option4", kRoutine4);
    m_autoRoutine.addOption("Option5", kRoutine5);
    SmartDashboard.putData("Auto choices", m_autoRoutine);
  }
  

  @Override

  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    m_autoRoutineSelected = m_autoRoutine.getSelected();
    System.out.println("Routine Selected: " + m_autoRoutineSelected);
  }

  @Override
  public void autonomousPeriodic() {
    if (m_autoRoutineSelected == "Option1") {
    }
    else if (m_autoRoutineSelected == "Option2") {
    }
    else if (m_autoRoutineSelected == "Option3") {

    }
    else if (m_autoRoutineSelected == "Option4") {

    }
    else if (m_autoRoutineSelected == "Option5"){
    }
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    m_Drivetrain.drive(new Translation2d(driver.getX(), -1*driver.getY()), driver.getRawAxis(2), false);
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}

  public void robotInitShuffleboard() {
    m_autoRoutine.setDefaultOption("MidDefault", kRoutine1);
    m_autoRoutine.addOption("CubeLeft", kRoutine2);
    m_autoRoutine.addOption("PreloadRight", kRoutine3);
    m_autoRoutine.addOption("Do Nothing", kRoutine4);   
    m_autoRoutine.addOption("MidDefault with mobility", kRoutine5);    
    SmartDashboard.putData("Auto Routine Selection", m_autoRoutine);
  }
}
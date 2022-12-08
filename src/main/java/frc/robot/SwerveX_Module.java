package frc.robot;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.geometry.Rotation2d;

public class SwerveX_Module {
    
    private static final double kWheelRadius = 0.0508; // this is in meters; need to update for us

    private final WPI_TalonFX m_driveMotor;
    private final WPI_TalonFX m_steerMotor;

    private final DigitalInput m_angleEncoderData;
    private final DutyCycleEncoder m_angleEncoder;

    /**
   * Constructs a SwerveModule with a drive motor, turning motor, angle encoder.
   *
   * @param driveMotorChannel PWM output for the drive motor.
   * @param steerMotorChannel PWM output for the turning motor.
   * @param angleEncoderChannel DIO input for the angle encoder.
   */
    public SwerveX_Module(
        int driveMotorChannel,
        int steerMotorChannel,
        int angleEncoderChannel) {

    m_driveMotor = new WPI_TalonFX(driveMotorChannel);
    m_steerMotor = new WPI_TalonFX(steerMotorChannel);
    m_angleEncoderData = new DigitalInput(angleEncoderChannel);
    m_angleEncoder = new DutyCycleEncoder(m_angleEncoderData);
    }

   /**
   * Returns the current state of the module.
   *
   * @return The current state of the module.
   */
    public SwerveModuleState getState() {
        return new SwerveModuleState(
            m_driveMotor.getSelectedSensorVelocity(), new Rotation2d(m_angleEncoder.get()));
           //m_driveEncoder.getRate(), new Rotation2d(m_turningEncoder.getDistance()));
    }

   /**
   * Returns the current position of the module.
   *
   * @return The current position of the module.
   */
    public SwerveModulePosition getPosition() {
        return new SwerveModulePosition(
            m_driveEncoder.getDistance(), new Rotation2d(m_turningEncoder.getDistance()));
    }

    /**
   * Sets the desired state for the module.
   *
   * @param desiredState Desired state with speed and angle.
   */
    public void setDesiredState(SwerveModuleState desiredState) {
        // Optimize the reference state to avoid spinning further than 90 degrees
        SwerveModuleState state =
            SwerveModuleState.optimize(desiredState, new Rotation2d(m_angleEncoder.get()));

        // Calculate the drive output from the drive PID controller.
        final double driveOutput =
            m_drivePIDController.calculate(m_driveEncoder.getRate(), state.speedMetersPerSecond);

        final double driveFeedforward = m_driveFeedforward.calculate(state.speedMetersPerSecond);

        // Calculate the turning motor output from the turning PID controller.
        final double turnOutput =
            m_turningPIDController.calculate(m_turningEncoder.getDistance(), state.angle.getRadians());

        final double turnFeedforward =
            m_turnFeedforward.calculate(m_turningPIDController.getSetpoint().velocity);

        m_driveMotor.setVoltage(driveOutput + driveFeedforward);
        m_turningMotor.setVoltage(turnOutput + turnFeedforward);
    }
}
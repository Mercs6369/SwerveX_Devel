package frc.robot.common.drivers;

import edu.wpi.first.wpilibj.AnalogGyro;
import frc.robot.common.math.Rotation2;

public final class AnalogGyroscope extends Gyroscope {

	private final AnalogGyro gyro;

	public AnalogGyroscope(int analogPort) {
		this(new AnalogGyro(analogPort));
	}

	public AnalogGyroscope(AnalogGyro gyro) {
		this.gyro = gyro;
	}

	@Override
	public void calibrate() {
		gyro.calibrate();
	}

	@Override
	public Rotation2 getUnadjustedAngle() {
		return Rotation2.fromDegrees(gyro.getAngle());
	}

	@Override
	public double getUnadjustedRate() {
		return gyro.getRate();
	}
}

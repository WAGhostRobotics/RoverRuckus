package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.TeleOp.Drive;
import org.firstinspires.ftc.teamcode.TeleOp.TeleOpDaddy;

import java.util.ArrayList;

public class DriveAuto {

    private ArrayList<DcMotor> motors;

    public DriveAuto(ArrayList<DcMotor> motors) {
        this.motors = motors;
    }

    public void move(MoveDirection direction, double power, double seconds) {
        switch (direction) {
            case FORWARD:
                Drive.MecanumArcade(motors, power, 0, 1, 0);
                break;
            case BACKWARD:
                Drive.MecanumArcade(motors, power, 0, -1, 0);
                break;
            case RIGHT:
                Drive.MecanumArcade(motors, power, 1, 0, 0);
                break;
            case LEFT:
                Drive.MecanumArcade(motors, power, -1, 0, 0);
                break;
        }
        new TeleOpDaddy().sleep((int) (seconds * 1000));
        Drive.stop(motors);
    }

    public void turn(TurnDirection direction, double power, double heading, Gyro gyro) {
        gyro.resetAngle();
        double currentHeading = gyro.getAngle();
        switch (direction) {
            case LEFT:
                Drive.Tank(motors, power, -1, 1);
                break;
            case RIGHT:
                Drive.Tank(motors, power, 1, -1);
                break;
        }
        while ((direction == TurnDirection.LEFT ? currentHeading < heading : currentHeading > -heading) && !new TeleOpDaddy().isStopRequested()) {
            currentHeading = gyro.getAngle();
        }
        Drive.stop(motors);
    }

    public void turn(TurnDirection direction, double power, double seconds) {
        switch (direction) {
            case LEFT:
                Drive.Tank(motors, power, -1, 1);
                break;
            case RIGHT:
                Drive.Tank(motors, power, 1, -1);
                break;
        }
        new TeleOpDaddy().sleep((int) (seconds * 1000));
        Drive.stop(motors);
    }

    public void stop(double seconds) {
        Drive.stop(motors);
        new TeleOpDaddy().sleep((int) (seconds * 1000));
    }

    public enum MoveDirection {
        FORWARD,
        BACKWARD,
        LEFT,
        RIGHT
    }

    public enum TurnDirection {
        LEFT,
        RIGHT
    }
}

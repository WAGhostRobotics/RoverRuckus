package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.ArrayList;

public class Drive {

    public static void Tank(ArrayList<DcMotor> motors, double multiplier, double left, double right) {
        motors.get(0).setPower(multiplier * left);
        motors.get(1).setPower(multiplier * left);
        motors.get(2).setPower(multiplier * right);
        motors.get(3).setPower(multiplier * right);
    }

    /**
     * Move motors based on left/right joystick from gamepad.
     *
     * @param motors array of motors [lf, lb, rf, rb]
     */
    public static void Tank(ArrayList<DcMotor> motors, Gamepad gamepad) {
        Tank(motors, gear(gamepad), -gamepad.left_stick_y, -gamepad.right_stick_y);
    }

    /**
     * Move motors based on drone-style input
     *
     * @param motors array of motors [lf, lb, rf, rb]
     */
    public static void MecanumArcade(ArrayList<DcMotor> motors, double multiplier, double x, double y, double turn) {
        double[] motorPowerArray = MecanumMath.vectorToMotors(x, y, turn);
        motors.get(0).setPower(multiplier * motorPowerArray[0]);
        motors.get(1).setPower(multiplier * motorPowerArray[1]);
        motors.get(2).setPower(multiplier * motorPowerArray[2]);
        motors.get(3).setPower(multiplier * motorPowerArray[3]);
    }

    /**
     * Move motors based on drone-style input from gamepad (left stick strafe, right stick turn)
     *
     * @param motors  array of motors [lf, lb, rf, rb]
     * @param gamepad the gamepad to control the robot with (gamepad1/gamepad2)
     */
    public static void MecanumArcade(ArrayList<DcMotor> motors, Gamepad gamepad) {
        MecanumArcade(motors, gear(gamepad), gamepad.left_stick_x, -gamepad.left_stick_y, gamepad.right_stick_x);
    }

    /**
     * Leo says: inwards left, outwards right (1/12/2019)
     * @param motors
     * @param multiplier
     * @param left
     * @param right
     * @param strafeLeft
     * @param strafeRight
     */
    public static void MecanumTank(ArrayList<DcMotor> motors, double multiplier, double left, double right, double strafeLeft, double strafeRight) {
        motors.get(0).setPower((multiplier * left) + (multiplier * strafeRight) - (multiplier * strafeLeft));
        motors.get(1).setPower((multiplier * left) - (multiplier * strafeRight) + (multiplier * strafeLeft));
        motors.get(2).setPower((multiplier * right) + (multiplier * strafeRight) - (multiplier * strafeLeft));
        motors.get(3).setPower((multiplier * right) - (multiplier * strafeRight) + (multiplier * strafeLeft));
    }

    /**
     * Move motors based on left/right joystick from gamepad.
     *
     * @param motors array of motors [lf, lb, rf, rb]
     */
    public static void MecanumTank(ArrayList<DcMotor> motors, Gamepad gamepad) {
        MecanumTank(motors, gear(gamepad), -gamepad.left_stick_y, -gamepad.right_stick_y, gamepad.left_trigger, gamepad.right_trigger);
    }

    public static void driveWithType(ArrayList<DcMotor> motors, Gamepad gamepad, DriveType type) {
        switch (type) {
            case TANK:
                Tank(motors, gamepad);
                break;
            case MECANUMARCADE:
                MecanumArcade(motors, gamepad);
                break;
            case MECANUMTANK:
                MecanumTank(motors, gamepad);
                break;
        }
    }

    private static double gear(Gamepad gamepad) {
        double gear;

        if (gamepad.left_bumper && gamepad.right_bumper) {
            gear = .25;
        } else if (gamepad.left_bumper || gamepad.right_bumper) {
            gear = .5;
        } else {
            gear = 1;
        }

        return gear;
        // TODO: find a way to report this variable to the user
    }

    public enum DriveType {
        TANK,
        MECANUMARCADE,
        MECANUMTANK
    }
}

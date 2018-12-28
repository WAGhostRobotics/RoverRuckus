package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

import java.util.ArrayList;

public class Drive {
    public  double multiplier;

    private static boolean wasPressed = false;

    private static final double INCREMENT = 0.1;

    /**
     *
     *
     * @param motors array of motors [lf, lb, rf, rb]
     * @param gamepad the gamepad to poll for input
     */
    void Tank(ArrayList<DcMotor> motors, Gamepad gamepad) {
        gear(gamepad);
        motors.get(0).setPower(multiplier * -gamepad.left_stick_y);
        motors.get(1).setPower(multiplier * -gamepad.left_stick_y);
        motors.get(2).setPower(multiplier * -gamepad.right_stick_y);
        motors.get(3).setPower(multiplier * -gamepad.right_stick_y);
    }

    /**
     * @author Leo
     *         <p>
     *         ======================================================================================
     *         Math involved in converting input values (vectors / directions) to the motor values
     *         required to move the Robot in that direction using mecanum wheels.
     *         ======================================================================================
     *         <p>
     *         Made by LEO (See? I'm helping)
     *         <p>
     *         The motor values are passed around as arrays containing 4 double values:
     *         <p>
     *         {motor 1, motor 2, motor 3, motor 4}
     *         <p>
     *         The motors correspond to the array elements like so:
     *         <p>
     *         [Motor 1]                    [Motor 3]
     *         [^Front of Robot^]
     *         [   ROBOT base   ]
     *         [Motor 2]                    [Motor 4]
     *         <p>
     *         (The directions of the mecanum wheels should form a diamond when viewed from above, NOT an 'X')
     *         (It is not certain if the code would still work with an 'X' arrangement, but it might)
     *         =======================================================================================
     *         <p>
     *         The length of the vectors passed to the methods do NOT correspond to drive distance.
     *         Instead, longer vectors will produce faster motion (but the motors can only handle values between -1 and 1)
     *         <p>
     *         If you are unfamiliar with vectors and how they are represented, consult the internet first.
     *         This code does not use any complicated vector math, only simple conversions and some
     *         addition
     *         =======================================================================================
     */
    void Mecanum(ArrayList<DcMotor> motors, Gamepad gamepad) {
        //===================================[Constants]===========================================
        //change these constants if the Robot is not behaving correctly!
        final double[] verticalDrive = {1, 1, -1, -1};   //the motor values that drive straight forward  (maybe backwards?)
        final double[] horizontalDrive = {1, -1, 1, -1};   //the motor values that strafe left             (maybe right?)
        final double[] turnDrive = {1, 1, 1, 1};        //the motor values that turn clockwise          (maybe counterclockwise?)

        gear(gamepad);
        // this part just makes editing the rest of the code easier
        double x_input = gamepad.left_stick_x;
        double y_input = gamepad.left_stick_y;
        double turn_input = ((y_input > 0) ? gamepad.right_stick_x : -gamepad.right_stick_x); //the turning is reversed if the Robot is driving backwards

        double[] driveMatrix = new double[4];

        double[] xMatrix = new double[4];
        double[] yMatrix = new double[4];
        double[] turnMatrix = new double[4];

        //sets values of matrices in x, y, and turning directions
        for (int i = 0; i < 4; i++) {
            xMatrix[i] = horizontalDrive[i] * x_input;
            yMatrix[i] = verticalDrive[i] * y_input;
            turnMatrix[i] = turnDrive[i] * turn_input;
        }

        //adds matrices to get motor values
        for (int i = 0; i < 4; i++) {
            driveMatrix[i] = xMatrix[i] + yMatrix[i] + turnMatrix[i];
        }

        motors.get(0).setPower(multiplier * -gamepad.left_stick_y);
        motors.get(1).setPower(multiplier * -gamepad.left_stick_y);
        motors.get(2).setPower(multiplier * -gamepad.right_stick_y);
        motors.get(3).setPower(multiplier * -gamepad.right_stick_y);
    }

    private void gear(Gamepad gamepad) {
        if (gamepad.left_bumper && gamepad.right_bumper) {
            multiplier = .25;
        } else if (gamepad.left_bumper || gamepad.right_bumper) {
            multiplier = .5;
        } else {
            multiplier = 1;
        }
    }
}

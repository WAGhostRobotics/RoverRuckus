package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.hardware.DcMotor;

public class TeleOpTools {
    public static void MotorToggle(DcMotor motor, double power, boolean condition1, boolean condition2) {
        if (condition1) {
            motor.setPower(power);
        } else if (condition2) {
            motor.setPower(-power);
        } else {
            motor.setPower(0);
        }
    }
    public static void MotorToggle(DcMotor motor, boolean condition1, boolean condition2) {
        MotorToggle(motor, 1, condition1, condition2);
    }
}

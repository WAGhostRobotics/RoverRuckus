package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.hardware.DcMotor;

public class TeleopTools {
    public static void MotorToggle(DcMotor motor, boolean condition1, boolean condition2) {
        if (condition1) {
            motor.setPower(1);
        } else if (condition2) {
            motor.setPower(-1);
        } else {
            motor.setPower(0);
        }
    }
}

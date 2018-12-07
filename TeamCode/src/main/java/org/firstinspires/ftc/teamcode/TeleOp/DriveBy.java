package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.List;

public class DriveBy {
    public static void Tank(List<DcMotor> motors, Gamepad gamepad) {
        motors.get(0).setPower(-gamepad.left_stick_y);
        motors.get(1).setPower(-gamepad.left_stick_y);
        motors.get(2).setPower(-gamepad.right_stick_y);
        motors.get(3).setPower(-gamepad.right_stick_y);
    }
}

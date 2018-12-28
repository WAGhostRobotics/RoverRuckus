package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Robot;

@TeleOp(name = "🅱️", group = "Robot")

public class TestTeleOp extends LinearOpMode {

    private double position = Robot.HOOK_INITIAL_POSITION;

    @Override
    public void runOpMode() throws InterruptedException {
        // Init the Robot's hardware (don't forget this!)
        Robot.init(hardwareMap);

        // Send diagnostics to user
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // Set drivemode to tank
            Drive.Tank(Robot.driveMotors, gamepad1);

            // Rack/Pinion Control (g1.dpad_up, dpad_down)
            if (gamepad1.dpad_up) {
                Robot.rackPinion.setPower(1);
            } else if (gamepad1.dpad_down) {
                Robot.rackPinion.setPower(-1);
            } else {
                Robot.rackPinion.setPower(0);
            }

            // Flipper Control (g1.a, b)
            if (gamepad1.a) {
                Robot.flipper.setPower(1);
            } else if (gamepad1.b) {
                Robot.flipper.setPower(-1);
            } else {
                Robot.flipper.setPower(0);
            }

            // Send diagnostics to user
            telemetry.addData("Status", "Running");
            telemetry.addData("Multiplier", Drive.multiplier);
            telemetry.update();
        }
    }
}

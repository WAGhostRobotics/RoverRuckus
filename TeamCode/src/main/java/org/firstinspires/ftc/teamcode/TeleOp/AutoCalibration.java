package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;

@TeleOp(name = "AutoCalibration")
public class AutoCalibration extends LinearOpMode {

    Drive.DriveType type = Drive.DriveType.TANK;

    double dump_position = 0.5;

    @Override
    public void runOpMode() throws InterruptedException {

        // Send diagnostics to user
        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        // Init the Robot's hardware (don't forget this!)
        Robot.init(hardwareMap);

        Robot.dump.setPosition(dump_position);

        // Send diagnostics to user
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.left_stick_button || gamepad2.left_stick_button) {
                Robot.dump.setPosition(Robot.dump.getPosition() - 0.05);
            } else if (gamepad1.right_stick_button || gamepad2.right_stick_button) {
                Robot.dump.setPosition(Robot.dump.getPosition() + 0.05);
            }

            // Send diagnostics to user
            telemetry.addData("Status", "Running");
            telemetry.addData("Position", Robot.dump.getPosition());
            telemetry.update();
        }
    }
}

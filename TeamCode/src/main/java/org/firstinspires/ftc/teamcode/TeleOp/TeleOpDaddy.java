package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;

public class TeleOpDaddy extends LinearOpMode {

    Drive.DriveType type = Drive.DriveType.TANK;

    @Override
    public void runOpMode() throws InterruptedException {

        // Send diagnostics to user
        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        // Init the Robot's hardware (don't forget this!)
        Robot.init(hardwareMap);

        // Send diagnostics to user
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            // Drive using set drivemode (g1.ls/rs, g1.lb/rb)
            Drive.driveWithType(Robot.driveMotors, gamepad1, type);

            // Rack/Pinion Control (g1.dpad_up/dpad_down)
            if (gamepad1.dpad_up || gamepad2.dpad_up) {
                Robot.rackPinion.setPower(1);
            } else if (gamepad1.dpad_down || gamepad2.dpad_down) {
                Robot.rackPinion.setPower(-1);
            } else {
                Robot.rackPinion.setPower(0);
            }

            // Linear Slide Spool Control (g1.dpad_left/dpad_right)
            if (gamepad1.dpad_left || gamepad2.dpad_left) {
                Robot.spool.setPower(1);
            } else if (gamepad1.dpad_right || gamepad2.dpad_right) {
                Robot.spool.setPower(-1);
            } else {
                Robot.spool.setPower(0);
            }

            // Linear Slide Swing Control (g1.dpad_left/dpad_right)
            if (gamepad1.b || gamepad2.b) {
                Robot.rotate1.setPower(.5);
                Robot.rotate2.setPower(.5);
            } else if (gamepad1.y || gamepad2.y) {
                Robot.rotate1.setPower(-.5);
                Robot.rotate2.setPower(-.5);
            } else {
                Robot.rotate1.setPower(0);
                Robot.rotate2.setPower(0);
            }

            // Linear Slide Intake Control (g1.dpad_left/dpad_right)
            if (gamepad1.a || gamepad2.a) {
                Robot.intake.setPower(1);
            } else if (gamepad1.x || gamepad2.x) {
                Robot.intake.setPower(-1);
            } else {
                Robot.intake.setPower(0);
            }

            if (gamepad1.left_stick_button || gamepad2.left_stick_button) {
                Robot.dump.setPosition(Robot.DUMP_DOWN);
            } else if (gamepad1.right_stick_button || gamepad2.right_stick_button) {
                Robot.dump.setPosition(Robot.DUMP_UP);
            }

            // Send diagnostics to user
            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}

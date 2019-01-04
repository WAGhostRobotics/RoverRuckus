package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;

public abstract class TeleOpWithControls extends LinearOpMode {

    protected Drive.DriveType type;

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

        type = (type != null) ? type : Drive.DriveType.TANK;

        waitForStart();

        while (opModeIsActive()) {

            // Drive using set drivemode
            Drive.driveWithType(Robot.driveMotors, gamepad1, type);

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
            telemetry.update();
        }
    }
}

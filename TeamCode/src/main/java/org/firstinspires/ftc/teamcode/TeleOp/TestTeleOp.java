package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Insight;

public class TestTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        // Init the robot's hardware (don't forget this!
        Insight.init(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // Set drivemode to tank
            DriveBy.Tank(Insight.driveMotors, gamepad1);

            // Rack/Pinion Control (g1.dpad_up, dpad_down)
            TeleopTools.MotorToggle(Insight.rackPinion, gamepad1.dpad_up, gamepad1.dpad_down);

            // Spinner/Intake Control (g1.a, b)
            TeleopTools.MotorToggle(Insight.intake, gamepad1.a, gamepad1.b);

            //
        }
    }
}

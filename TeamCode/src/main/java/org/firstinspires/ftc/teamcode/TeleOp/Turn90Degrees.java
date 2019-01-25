package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Auto.DriveAuto;
import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.Robot;

@TeleOp(name = "Turn90Degrees")
public class Turn90Degrees extends LinearOpMode {

    DriveAuto drivetrain = new DriveAuto(Robot.driveMotors);

    @Override
    public void runOpMode() throws InterruptedException {

        // Send diagnostics to user
        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        Robot.init(hardwareMap);

        // Send diagnostics to user
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                drivetrain.turn(DriveAuto.TurnDirection.LEFT, .5, 90, BNO055IMU.AngleUnit.DEGREES);
            } else if (gamepad1.b) {
                drivetrain.turn(DriveAuto.TurnDirection.RIGHT, .5, 90, BNO055IMU.AngleUnit.DEGREES);
            }
            // Send diagnostics to user
            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}

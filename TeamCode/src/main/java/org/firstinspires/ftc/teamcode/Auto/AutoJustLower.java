package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.TeleOp.Drive;

@Autonomous(name = "AutoJustLower")
public class AutoJustLower extends CVLinearOpMode {

    Gyro gyro;
    DriveAuto drivetrain = new DriveAuto(Robot.driveMotors);
    AutonomousDaddy.StartLocation startLocation = AutonomousDaddy.StartLocation.CRATER;

    @Override
    public void runOpMode() throws InterruptedException {

        // -----[Program is in state INITIALIZING]-----

        // Send diagnostics to user
        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        Robot.init(hardwareMap);
        initDoge();
        gyro = new Gyro(Robot.imu);
        telemetry.update();

        // Send diagnostics to user
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        doge.enable();

        while (!isStarted()) {
            // Call CV detection code here
            dogeScanForPosition();
            telemetry.addData("Mineral", goldLocation);
            telemetry.update();
        }

        doge.disable();
        // -----[Program is in state STARTED]-----

        lowerLift();

        moveRobotTowardsMineral();

    }

    void lowerLift() {
        Robot.rackPinion.setPower(1);
        telemetry.addData("Lift", "Lowering");
        telemetry.update();
        sleep(3700);
        Robot.rackPinion.setPower(0);
        telemetry.addData("Lift", "Lowered");
        telemetry.update();

        Robot.rotate1.setPower(.5);
        Robot.rotate2.setPower(.5);
        sleep(250);
        Robot.rotate1.setPower(0);
        Robot.rotate2.setPower(0);

        sleep(500);
    }

    void moveRobotTowardsMineral() {
        drivetrain.move(DriveAuto.MoveDirection.LEFT, .6, .5);
        Drive.stop(Robot.driveMotors);
        drivetrain.move(DriveAuto.MoveDirection.BACKWARD, .5, .5);
        Drive.stop(Robot.driveMotors);
        drivetrain.move(DriveAuto.MoveDirection.RIGHT, .5, .5);
        sleep(1000);
    }
}

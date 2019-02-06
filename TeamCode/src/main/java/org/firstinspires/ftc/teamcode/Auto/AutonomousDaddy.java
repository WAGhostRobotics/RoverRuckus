package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.TeleOp.Drive;

public class AutonomousDaddy extends CVLinearOpMode {
    Gyro gyro;
    DriveAuto drivetrain = new DriveAuto(Robot.driveMotors);
    StartLocation startLocation = StartLocation.CRATER;

    @Override
    public void runOpMode() throws InterruptedException {
        // -----[Program is in state INITIALIZING]-----

        // Send diagnostics to user
        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        Robot.init(hardwareMap);
        initCV();
        gyro = new Gyro(Robot.imu);
        telemetry.update();

        // Send diagnostics to user
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        tfod.activate();

        while (!isStarted()) {
            // Call CV detection code here
            scanForPosition();
            telemetry.addData("Mineral", blockPosition);
            telemetry.update();
        }

        tfod.shutdown();
        // -----[Program is in state STARTED]-----

        lowerLift();

        moveRobotTowardsMineral();

        //knockMineral();

        moveRobotTowardsDepot();

        dropTeamMarker();

        moveRobotTowardsCrater();

        parkOnCrater();
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
        drivetrain.move(DriveAuto.MoveDirection.LEFT, .5, .5);
        Drive.stop(Robot.driveMotors);
        drivetrain.move(DriveAuto.MoveDirection.BACKWARD, .5, .75);
        Drive.stop(Robot.driveMotors);
        drivetrain.move(DriveAuto.MoveDirection.RIGHT, .5, .5);
        sleep(1000);
    }

    void knockMineral() {
        telemetry.addData("Mineral", "Knocking" + blockPosition);
        telemetry.update();
        // Align with block
        switch (blockPosition) {
            case LEFT:
                drivetrain.move(DriveAuto.MoveDirection.LEFT, .5, 1);
                Drive.stop(Robot.driveMotors);
                break;
            case RIGHT:
                drivetrain.move(DriveAuto.MoveDirection.RIGHT, .5, 1);
                Drive.stop(Robot.driveMotors);
                break;
            case UNKNOWN:
                // fall through
            case CENTER:
                break;
        }
        drivetrain.stop(.5);
        // Knock block
        drivetrain.move(DriveAuto.MoveDirection.BACKWARD, .5, 1);
        drivetrain.stop(.5);
        // Move back to previous position
        drivetrain.move(DriveAuto.MoveDirection.FORWARD, .5, 1);
        telemetry.addData("Mineral", "Knocked" + blockPosition);
        telemetry.update();
    }

    void moveRobotTowardsDepot() {
        drivetrain.turn(DriveAuto.TurnDirection.RIGHT, .5, 90, gyro);
        drivetrain.move(DriveAuto.MoveDirection.FORWARD, 1, 1.5);
        drivetrain.turn(DriveAuto.TurnDirection.LEFT, .5, 45, gyro);
        switch (startLocation) {
            case CRATER:
                drivetrain.move(DriveAuto.MoveDirection.FORWARD, 1, 1.75);
                break;

            case DEPOT:
                drivetrain.move(DriveAuto.MoveDirection.BACKWARD, 1, 1.75);
                break;
        }
    }

    void dropTeamMarker() {
        switch (startLocation) {
            case CRATER:
                drivetrain.turn(DriveAuto.TurnDirection.LEFT, .5, 45, gyro);
                break;

            case DEPOT:
                drivetrain.turn(DriveAuto.TurnDirection.RIGHT, .5, 45, gyro);
                break;
        }
        Robot.dump.setPosition(Robot.DUMP_DOWN);
        sleep(500);
        switch (startLocation) {
            case CRATER:
                drivetrain.turn(DriveAuto.TurnDirection.RIGHT, .5, 45, gyro);
                break;

            case DEPOT:
                drivetrain.turn(DriveAuto.TurnDirection.LEFT, .5, 45, gyro);
                break;
        }
        telemetry.addData("Team Marker", "Dropped");
        telemetry.update();
    }

    void moveRobotTowardsCrater() {
        switch (startLocation) {
            case CRATER:
                drivetrain.move(DriveAuto.MoveDirection.BACKWARD, 1, 2.25);
                break;

            case DEPOT:
                drivetrain.move(DriveAuto.MoveDirection.FORWARD, 1, 2.25);
                drivetrain.turn(DriveAuto.TurnDirection.LEFT, 1, 150, gyro);
                break;
        }
    }

    void parkOnCrater() {
        Robot.rotate1.setPower(.5);
        Robot.rotate2.setPower(.5);
        sleep(1000);
        Robot.rotate1.setPower(0);
        Robot.rotate2.setPower(0);

        /*
        Robot.spool.setPower(1);
        sleep(2000);
        Robot.spool.setPower(0);
        */

        telemetry.addData("Crater", "Parked on");
        telemetry.update();
    }

    enum StartLocation {
        DEPOT,
        CRATER
    }
}

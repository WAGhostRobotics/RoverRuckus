package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.TeleOp.Drive;

public class AutonomousDaddy extends CVLinearOpMode {
    Gyro gyro;
    DriveAuto drivetrain = new DriveAuto(Robot.driveMotors);
    StartLocation startLocation = StartLocation.CRATER;

    ElapsedTime elapsedTime = new ElapsedTime();



    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        Robot.init(hardwareMap);
        // To use TensorFlow (slower, more accurate)
        initTfod();

        // To use DogeCV (faster, slightly less accurate?, memory leaks)
        //initDoge();

        gyro = new Gyro(Robot.imu);
        telemetry.update();

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // To use TensorFlow (slower, more accurate)
        tfod.activate();

        //doge.enable();


        while (!isStarted() && !isStopRequested()) {
            // Call CV detection code here
            tfScanForPosition();
            telemetry.addData("Mineral", goldLocation);
            telemetry.update();
        }


        // To use TensorFlow (slower, more accurate)
        tfod.shutdown();

        // if (doge != null) doge.disable();

        waitForStart();

        // -----[Program is in state STARTED]-----

        lowerLift();

        // scanForMineral();

        moveRobotTowardsMineral();

        knockMineral();

        moveRobotTowardsDepot();

        dropTeamMarker();

        moveRobotTowardsCrater();

        parkOnCrater();
    }

    void lowerLift() {
        Robot.rackPinion.setPower(1);
        telemetry.addData("Lift", "Lowering");
        telemetry.update();
        sleep(3500);
        Robot.rackPinion.setPower(0);
        telemetry.addData("Lift", "Lowered");
        telemetry.update();

        Robot.rotate1.setPower(.5);
        Robot.rotate2.setPower(.5);
        sleep(550);
        Robot.rotate1.setPower(0);
        Robot.rotate2.setPower(0);

        sleep(250);
    }

    void scanForMineral() {

        // To use TensorFlow (slower, more accurate)
        tfod.activate();

        elapsedTime.reset();

        while (elapsedTime.seconds() < 1) {
            // Call CV detection code here
            tfScanForPosition();
            // dogeScanForPosition();
            telemetry.addData("Mineral", goldLocation);
            telemetry.update();
        }

        tfod.shutdown();
    }

    void moveRobotTowardsMineral() {
        drivetrain.move(DriveAuto.MoveDirection.LEFT, .5, .35);
        Drive.stop(Robot.driveMotors);
        drivetrain.move(DriveAuto.MoveDirection.BACKWARD, .5, 1);
        Drive.stop(Robot.driveMotors);
        drivetrain.move(DriveAuto.MoveDirection.RIGHT, .5, .4);
    }

    void knockMineral() {
        telemetry.addData("Mineral", "Knocking" + goldLocation);
        telemetry.update();
        // Align with block
        switch (goldLocation) {
            case LEFT:
                drivetrain.move(DriveAuto.MoveDirection.RIGHT, .5, 1);
                Drive.stop(Robot.driveMotors);
                break;
            case RIGHT:
                drivetrain.move(DriveAuto.MoveDirection.LEFT, .5, 1);
                Drive.stop(Robot.driveMotors);
                break;
            case CENTER:
                break;
            case UNKNOWN:
                return;
        }
        // Knock block
        drivetrain.move(DriveAuto.MoveDirection.BACKWARD, .5, 1);
        // Move back to previous position
        drivetrain.move(DriveAuto.MoveDirection.FORWARD, .5, 1);
        switch (goldLocation) {
            case LEFT:
                drivetrain.move(DriveAuto.MoveDirection.LEFT, .5, 1);
                Drive.stop(Robot.driveMotors);
                break;
            case RIGHT:
                drivetrain.move(DriveAuto.MoveDirection.RIGHT, .5, 1);
                Drive.stop(Robot.driveMotors);
                break;
            case CENTER:
                break;
            case UNKNOWN:
                return;
        }
        telemetry.addData("Mineral", "Knocked" + goldLocation);
        telemetry.update();
    }

    void moveRobotTowardsDepot() {
        drivetrain.turn(DriveAuto.TurnDirection.RIGHT, .5, 88, gyro);
        drivetrain.move(DriveAuto.MoveDirection.FORWARD, 1, 1.25);
        drivetrain.turn(DriveAuto.TurnDirection.LEFT, .5, 47, gyro);
        switch (startLocation) {
            case CRATER:
                drivetrain.move(DriveAuto.MoveDirection.FORWARD, 1, 1.8);
                break;

            case DEPOT:
                drivetrain.move(DriveAuto.MoveDirection.BACKWARD, 1, 1.8);
                break;
        }
    }

    void dropTeamMarker() {

        switch (startLocation) {
            case CRATER:
                drivetrain.turn(DriveAuto.TurnDirection.LEFT, .5, 40, gyro);
                break;

            case DEPOT:
                drivetrain.turn(DriveAuto.TurnDirection.RIGHT, .5, 40, gyro);
                break;
        }
        Robot.dump.setPosition(Robot.DUMP_DOWN);
        sleep(400);

        switch (startLocation) {
            case CRATER:
                drivetrain.turn(DriveAuto.TurnDirection.RIGHT, .5, 37, gyro);
                break;

            case DEPOT:
                drivetrain.turn(DriveAuto.TurnDirection.LEFT, .5, 37, gyro);
                break;
        }
        Robot.dump.setPosition(Robot.DUMP_UP);
        telemetry.addData("Team Marker", "Dropped");
        telemetry.update();
    }

    void moveRobotTowardsCrater() {
        switch (startLocation) {
            case CRATER:
                drivetrain.move(DriveAuto.MoveDirection.BACKWARD, 1, 2.5);
                break;

            case DEPOT:
                drivetrain.move(DriveAuto.MoveDirection.FORWARD, 1, 2.5);
                drivetrain.turn(DriveAuto.TurnDirection.LEFT, .75, 165, gyro);
                break;
        }
    }

    void parkOnCrater() {
        Robot.rotate1.setPower(.5);
        Robot.rotate2.setPower(.5);
        sleep(750);
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

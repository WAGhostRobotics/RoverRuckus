package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.TeleOp.Drive;

@Autonomous(name = "Auto")
public class Auto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        // Send diagnostics to user
        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        Robot.init(hardwareMap);
        telemetry.addData(" ", " ");
        telemetry.update();

        // Call CV detection code here
        telemetry.addData("Mineral", "[LOCATION]");
        telemetry.update();

        // Send diagnostics to user
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        lowerLift(4.25);

        moveRobotTowardsMineral();

        knockMineral();

        // Move robot towards depot

        dropTeamMarker();

        // Drive to crater

        parkOnCrater();
    }

    void lowerLift(double seconds) {
        Robot.rackPinion.setPower(1);
        telemetry.addData("Lift", "Lowering");
        telemetry.update();
        sleep((int) (seconds * 1000));
        Robot.rackPinion.setPower(0);
        telemetry.addData("Lift", "Lowered");
        telemetry.update();
    }

    void knockMineral() {
        telemetry.addData("Mineral", "Knocking [LOCATION]");
        telemetry.update();
        telemetry.addData("Mineral", "Knocked [LOCATION]");
        telemetry.update();
    }

    void moveRobotTowardsMineral() {
        Drive.MecanumTank(Robot.driveMotors, 0, 0, 0, 1, 0);
        sleep(1 * 1000);
        Drive.Tank(Robot.driveMotors, 1, -1, -1);
        sleep(1 * 1000);
        Drive.Tank(Robot.driveMotors, 0, 0, 0);
    }

    void dropTeamMarker() {
        Robot.dump.setPosition(Robot.DUMP_DOWN);
        telemetry.addData("Team Marker", "Dropped");
        telemetry.update();
    }

    void parkOnCrater() {
        telemetry.addData("Crater", "Parked on");
        telemetry.update();
    }
}

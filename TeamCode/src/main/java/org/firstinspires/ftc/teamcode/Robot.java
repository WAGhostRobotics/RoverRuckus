package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Auto.Gyro;

import java.util.ArrayList;

public class Robot {
    public static HardwareMap hardwareMap;

    // Drive
    public static DcMotor dFrontLeft;
    public static DcMotor dFrontRight;
    public static DcMotor dBackLeft;
    public static DcMotor dBackRight;

    // Motors array [in order: lf, lr, rf, rr]
    public static ArrayList<DcMotor> driveMotors = new ArrayList<>();

    // Lift
    public static DcMotor rackPinion;

    // Linear Slide
    public static DcMotor rotate1;
    public static DcMotor rotate2;
    public static DcMotor spool;
    public static CRServo intake;

    // Miriam's Slide
    public static Servo dump;
    public static final double DUMP_UP = 0.2;
    public static final double DUMP_DOWN = 0.6;

    // Gyroscope
    public static BNO055IMU imu;
    public static Gyro gyro;

    /**
     *
     * @param hwMap the HardwareMap to pass in from the OpMode
     */
    public static void init(HardwareMap hwMap) {
        hardwareMap = hwMap;
        dFrontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        dFrontRight = hardwareMap.get(DcMotor.class, "frontRight");
        dBackLeft = hardwareMap.get(DcMotor.class, "backLeft");
        dBackRight = hardwareMap.get(DcMotor.class, "backRight");

        dFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        dFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        dBackLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        dBackRight.setDirection(DcMotorSimple.Direction.FORWARD);

        driveMotors.add(dFrontLeft);
        driveMotors.add(dFrontRight);
        driveMotors.add(dBackLeft);
        driveMotors.add(dBackRight);

        rackPinion = hardwareMap.get(DcMotor.class, "rp");
        rackPinion.setDirection(DcMotorSimple.Direction.REVERSE);

        rotate1 = hardwareMap.get(DcMotor.class, "rotate1");
        rotate1.setDirection(DcMotorSimple.Direction.FORWARD);
        rotate2 = hardwareMap.get(DcMotor.class, "rotate2");
        rotate2.setDirection(DcMotorSimple.Direction.FORWARD);

        spool = hardwareMap.get(DcMotor.class, "sp");
        spool.setDirection(DcMotorSimple.Direction.FORWARD);

        intake = hardwareMap.get(CRServo.class, "in");
        intake.setDirection(DcMotorSimple.Direction.FORWARD);

        dump = hardwareMap.get(Servo.class, "du");
        dump.setDirection(Servo.Direction.FORWARD);
        dump.setPosition(DUMP_UP);

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        gyro = new Gyro(imu);
    }
}

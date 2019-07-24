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
    public static final double DUMP_UP = 0.1;
    public static final double DUMP_DOWN = 0.9;

    // Gyroscope
    public static BNO055IMU imu;

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


        dFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dBackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dBackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        /*
        dFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        dFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        dBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        dBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        */

        driveMotors.add(dFrontLeft);
        driveMotors.add(dFrontRight);
        driveMotors.add(dBackLeft);
        driveMotors.add(dBackRight);

        rackPinion = hardwareMap.get(DcMotor.class, "rp");
        rackPinion.setDirection(DcMotorSimple.Direction.REVERSE);
        rackPinion.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rackPinion.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rotate1 = hardwareMap.get(DcMotor.class, "rotate1");
        rotate1.setDirection(DcMotorSimple.Direction.FORWARD);
        rotate1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rotate2 = hardwareMap.get(DcMotor.class, "rotate2");
        rotate2.setDirection(DcMotorSimple.Direction.FORWARD);
        rotate2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        spool = hardwareMap.get(DcMotor.class, "sp");
        spool.setDirection(DcMotorSimple.Direction.FORWARD);
        //spool.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intake = hardwareMap.get(CRServo.class, "in");
        intake.setDirection(DcMotorSimple.Direction.FORWARD);

        dump = hardwareMap.get(Servo.class, "du");
        dump.setDirection(Servo.Direction.FORWARD);
        dump.setPosition(DUMP_UP);

        imu = hardwareMap.get(BNO055IMU.class, "imu");
    }
}

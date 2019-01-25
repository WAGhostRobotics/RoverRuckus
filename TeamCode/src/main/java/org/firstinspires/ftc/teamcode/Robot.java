package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Auto.Gyro;

import java.util.ArrayList;
import java.util.Arrays;

public class Robot {
    public static HardwareMap hardwareMap;

    // Drive
    public static DcMotor dLeftFront;
    public static DcMotor dLeftRear;
    public static DcMotor dRightFront;
    public static DcMotor dRightRear;

    // Motors array [in order: lf, lr, rf, rr]
    public static ArrayList<DcMotor> driveMotors = new ArrayList<>();

    // Lift
    public static DcMotor rackPinion;

    // Linear Slide
    public static DcMotor linearSlide;
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
        dLeftFront = hardwareMap.get(DcMotor.class, "lf");
        dLeftRear = hardwareMap.get(DcMotor.class, "lr");
        dRightFront = hardwareMap.get(DcMotor.class, "rf");
        dRightRear = hardwareMap.get(DcMotor.class, "rr");

        dLeftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        dLeftRear.setDirection(DcMotorSimple.Direction.REVERSE);
        dRightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        dRightRear.setDirection(DcMotorSimple.Direction.FORWARD);

        driveMotors.add(dLeftFront);
        driveMotors.add(dLeftRear);
        driveMotors.add(dRightFront);
        driveMotors.add(dRightRear);

        rackPinion = hardwareMap.get(DcMotor.class, "rp");
        rackPinion.setDirection(DcMotorSimple.Direction.REVERSE);

        linearSlide = hardwareMap.get(DcMotor.class, "ls");
        linearSlide.setDirection(DcMotorSimple.Direction.FORWARD);

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

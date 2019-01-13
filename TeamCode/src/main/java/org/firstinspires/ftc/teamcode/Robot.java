package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

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
    }
}

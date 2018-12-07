package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Arrays;
import java.util.List;

public class Insight {
    public static HardwareMap hardwareMap;

    // Drive
    public static DcMotor dLeftFront;
    public static DcMotor dLeftRear;
    public static DcMotor dRightFront;
    public static DcMotor dRightRear;

    // Motors array [in order: lf, lr, rf, rr]
    public static List<DcMotor> driveMotors = Arrays.asList(dLeftFront, dLeftRear, dRightFront, dRightRear);

    // Lift
    public static DcMotor rackPinion;

    // Flipper Arm
    public static DcMotor flipper;

    // Intake
    public static DcMotor intake;

    public static void init(HardwareMap hwMap) {
        hardwareMap = hwMap;
        dLeftFront = hardwareMap.get(DcMotor.class, "lf");
        dLeftRear = hardwareMap.get(DcMotor.class, "lr");
        dRightFront = hardwareMap.get(DcMotor.class, "rf");
        dRightFront = hardwareMap.get(DcMotor.class, "rr");

        dLeftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        dLeftRear.setDirection(DcMotorSimple.Direction.FORWARD);
        dRightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        dRightRear.setDirection(DcMotorSimple.Direction.REVERSE);


        rackPinion = hardwareMap.get(DcMotor.class, "rp");

        flipper = hardwareMap.get(DcMotor.class, "fl");

        intake = hardwareMap.get(DcMotor.class, "in");
    }
}

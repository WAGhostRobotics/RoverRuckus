package org.firstinspires.ftc.teamcode;

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
    public static ArrayList<DcMotor> driveMotors = new ArrayList<DcMotor>();

    // Lift
    public static DcMotor rackPinion;
    public static Servo hook;
    public static final double HOOK_INITIAL_POSITION = 0.5;

    // Flipper Arm
    public static DcMotor flipper;

    // Intake
    public static DcMotor intake;

    /**
     *
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
        hook = hardwareMap.get(Servo.class, "hook");
        hook.setPosition(HOOK_INITIAL_POSITION);

        flipper = hardwareMap.get(DcMotor.class, "fl");
        flipper.setDirection(DcMotorSimple.Direction.REVERSE);

        // intake = hardwareMap.get(DcMotor.class, "in");
    }
}

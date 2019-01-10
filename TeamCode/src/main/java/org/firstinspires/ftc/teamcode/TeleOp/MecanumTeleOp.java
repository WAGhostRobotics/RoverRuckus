package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@SuppressWarnings("unused")
@TeleOp(name = "Mecanum TeleOp")
public class MecanumTeleOp extends TeleOpDaddy {
    @Override
    public void runOpMode() throws InterruptedException {
        super.type = Drive.DriveType.MECANUM;
        super.runOpMode();
    }
}

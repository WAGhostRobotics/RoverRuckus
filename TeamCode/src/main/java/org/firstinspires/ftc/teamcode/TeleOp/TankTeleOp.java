package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@SuppressWarnings("unused")
@TeleOp(name = "Tank TeleOp")
public class TankTeleOp extends TeleOpDaddy {
    @Override
    public void runOpMode() throws InterruptedException {
        super.type = Drive.DriveType.TANK;
        super.runOpMode();
    }
}

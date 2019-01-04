package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Tank TeleOp")
public class TankTeleOp extends TeleOpWithControls {
    @Override
    public void runOpMode() throws InterruptedException {
        super.type = Drive.DriveType.TANK;
        super.runOpMode();
    }
}

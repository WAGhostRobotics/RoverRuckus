package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@SuppressWarnings("unused")
@TeleOp(name = "MecanumTank TeleOp")
public class MecanumTankTeleOp extends TeleOpDaddy {
    @Override
    public void runOpMode() throws InterruptedException {
        super.type = Drive.DriveType.MECANUMTANK;
        super.runOpMode();
    }
}

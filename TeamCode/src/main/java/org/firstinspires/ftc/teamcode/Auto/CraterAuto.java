package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Crater Auto")
public class CraterAuto extends AutonomousDaddy {

    @Override
    public void runOpMode() throws InterruptedException {
        startLocation = StartLocation.CRATER;
        super.runOpMode();
    }
}

package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Depot Auto")
public class DepotAuto extends AutonomousDaddy {

    @Override
    public void runOpMode() throws InterruptedException {
        startLocation = StartLocation.DEPOT;
        super.runOpMode();
    }
}

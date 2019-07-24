package org.firstinspires.ftc.teamcode.Auto;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.SamplingOrderDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "SamplingOrderExample")
public class SamplingOrderExample extends LinearOpMode {

    // Detector object
    private SamplingOrderDetector detector;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "DogeCV 2018.0 - Sampling Order Example");

        // Setup doge
        detector = new SamplingOrderDetector(); // Create the doge
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance()); // Initialize doge with app context and camera
        detector.useDefaults(); // Set doge to use default settings

        detector.downscale = 0.4; // How much to downscale the input frames

        // Optional tuning
        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
        //doge.perfectAreaScorer.perfectArea = 10000; // if using PERFECT_AREA scoring
        detector.maxAreaScorer.weight = 0.001;

        detector.ratioScorer.weight = 15;
        detector.ratioScorer.perfectRatio = 1.0;

        detector.enable(); // Start doge

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Current Order", detector.getCurrentOrder().toString()); // The current result for the frame
            telemetry.addData("Last Order", detector.getLastOrder().toString()); // The last known result
        }

        detector.disable();

    }
}

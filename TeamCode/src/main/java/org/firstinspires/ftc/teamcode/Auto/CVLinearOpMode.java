package org.firstinspires.ftc.teamcode.Auto;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.SamplingOrderDetector;
import com.disnodeteam.dogecv.detectors.roverrukus.SamplingOrderDetector.GoldLocation;
import com.disnodeteam.dogecv.filters.LeviColorFilter;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

public abstract class CVLinearOpMode extends LinearOpMode {
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    // Vuforia license key
    private static final String VUFORIA_KEY = "AZoFZrX/////AAABmWAZ6Ga0C06Shdn8BBIra9kpUV8YgKa+C9ggtup145LPUfAn+k1okjUV/NBZyx3NUuAp1XwZ8HY9v4SqbeLjhwtMhgWTMXV+3EeLgvVgrH+VmqyVrX58OkGdFsu4cC0QZgoKFuqPTSfefFASyXnAhOoAuXod/dUb4v1Rf2CJl7M4whSiLBBFFYn0JIwTNjnJ6H+2wYnQPTCPqfuh96XpeD7vUMbJNrlafV/wIuodAXJnTjw6IY0So5dDlHFq2Sx2fsxdB0kLxpYQjTomDoH8QzuWWbZawqJEMuNEHDH4rTJmKlx9iJZDeMnnMml7VOARG4nf04KMCvZkKWn+xouU51PkxdvHqutxLz0jkyCzk1lj";
    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    protected VuforiaLocalizer vuforia;
    /**
     * {@link #tfod} is the variable we will use to store our instance of the Tensor Flow Object
     * Detection engine.
     */
    protected TFObjectDetector tfod;
    // Detector object
    protected SamplingOrderDetector doge;
    protected WebcamName bertha;

    GoldLocation goldLocation = GoldLocation.UNKNOWN;

    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Bertha");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
    }

    /**
     * Initialize the Tensor Flow Object Detection engine.
     */
    protected void initTfod() {
        initVuforia();
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }

    protected void tfScanForPosition() {
        if (tfod != null) {
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                telemetry.addData("# Object Detected", updatedRecognitions.size());
                if (updatedRecognitions.size() >= 2) {
                    int goldMineralX = -1;
                    int silverMineral1X = -1;
                    int silverMineral2X = -1;
                    for (Recognition recognition : updatedRecognitions) {
                        if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                            goldMineralX = (int) recognition.getLeft();
                        } else if (silverMineral1X == -1) {
                            silverMineral1X = (int) recognition.getLeft();
                        } else {
                            silverMineral2X = (int) recognition.getLeft();
                        }
                    }

                    if (goldMineralX != -1 && goldMineralX < silverMineral1X) {
                        goldLocation = GoldLocation.CENTER;
                    } else if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral1X < goldMineralX) {
                        goldLocation = GoldLocation.RIGHT;
                    } else if (silverMineral1X != -1 && silverMineral2X != -1) {
                        goldLocation = GoldLocation.LEFT;
                    }

                    /*
                    if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                        if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                            telemetry.addData("Gold Mineral Position", "Left");
                            goldLocation = GoldLocation.LEFT;
                        } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                            telemetry.addData("Gold Mineral Position", "Right");
                            goldLocation = GoldLocation.RIGHT;
                        } else {
                            telemetry.addData("Gold Mineral Position", "Center");
                            goldLocation = GoldLocation.CENTER;
                        }
                    }
                    */

                } else {
                    goldLocation = GoldLocation.UNKNOWN;
                }
                telemetry.update();
            }
        }
    }

    protected void initDoge() {
        // Setup doge
        bertha = hardwareMap.get(WebcamName.class, "Bertha"); //Retrieves the webcam from the hardware map
        doge = new SamplingOrderDetector(); // Create the doge
        doge.VUFORIA_KEY = VUFORIA_KEY;
        doge.init(hardwareMap.appContext, CameraViewDisplay.getInstance(), DogeCV.CameraMode.WEBCAM, false, bertha); // Initialize doge with app context and camera
        doge.useDefaults(); // Set detector to use default settings

        doge.downscale = 0.4; // How much to downscale the input frames
        doge.whiteFilter = new LeviColorFilter(LeviColorFilter.ColorPreset.WHITE);
        // Optional tuning
        doge.areaScoringMethod = DogeCV.AreaScoringMethod.PERFECT_AREA; // Can also be PERFECT_AREA
        doge.perfectAreaScorer.perfectArea = 1400; // if using PERFECT_AREA scoring
        doge.maxAreaScorer.weight = 0.001;

        doge.ratioScorer.weight = 15;
        doge.ratioScorer.perfectRatio = 1.0;
    }

    protected void dogeScanForPosition() {
        goldLocation = doge.getLastOrder();
        telemetry.addData("Current Order", doge.getCurrentOrder().toString()); // The current result for the frame
        telemetry.addData("Last Order", doge.getLastOrder().toString()); // The last known result
    }
}

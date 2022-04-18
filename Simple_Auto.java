package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Randobot: Simple Autonomus", group="Randobot_Test_Later")

public class Simple_Auto extends LinearOpMode {
    Randobot robot = new Randobot();
    //private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        waitForStart();

        System.out.println("started");

        robot.Left.setPower(1);
        robot.Right.setPower(1);
        sleep(1000);

        robot.Left.setPower(0.5);
        robot.Right.setPower(-0.5);
        sleep(2000);

        robot.Left.setPower(1);
        robot.Right.setPower(1);
        sleep(1000);

        robot.Left.setPower(0);
        robot.Right.setPower(0);

        int pos = 1170;

        robot.Arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.Arm.setTargetPosition(1170/10);
        robot.Arm.setPower(1.0);
        robot.Arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(robot.Arm.isBusy()){
            telemetry.addData("is running","0");
            telemetry.update();
        }

        robot.Arm.setPower(0);

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(100);

    }
}

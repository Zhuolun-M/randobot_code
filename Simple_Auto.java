package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(100);

    }
}

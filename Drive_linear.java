package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

class Randobot {
    public DcMotor Left = null;
    public DcMotor Right = null;
    public Servo Claw = null;
    public DcMotor Arm = null;
    public DcMotor Spinner = null;

    public static double Claw_Home = 0.5;
    //public static double Claw_Min = 0.0;
    //public static double Claw_Max = 0.5;

    HardwareMap map = null;

    public void init(HardwareMap maps) {
        map = maps;
        Left = maps.get(DcMotor.class, "LM");
        Right = maps.get(DcMotor.class, "RM");
        Claw = maps.servo.get("Claw");
        Arm = maps.get(DcMotor.class, "Arm");
        Spinner = maps.get(DcMotor.class, "spinner");

        Left.setDirection(DcMotorSimple.Direction.REVERSE);
        Right.setDirection(DcMotorSimple.Direction.FORWARD);
        Arm.setDirection(DcMotorSimple.Direction.FORWARD);
        Spinner.setDirection(DcMotorSimple.Direction.FORWARD);


        Left.setPower(0.0);
        Right.setPower(0.0);
        Claw.setPosition(Claw_Home);
        Arm.setPower(0.0);
        Spinner.setPower(0.0);

        Left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Spinner.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }
}

@TeleOp(name="Drive", group="Drive")

public class Drive_linear extends LinearOpMode{
    Randobot robot = new Randobot();
    //double x;
    double y;
    double rx;
    double ClawPosition = robot.Claw_Home;
    double Armpos = 0.0;
    double Spinpos = 0.0;
    final double Claw_Speed = 0.05;
    final double Arm_Speed = 0.05;
    final double Spin_Speed = 0.2;

    @Override
    public void runOpMode(){
        robot.init(hardwareMap);
        telemetry.addData("Say", "Hello");
        telemetry.update();

        waitForStart();

        while(opModeIsActive()){

            y = -gamepad1.left_stick_y;
            rx = gamepad1.right_stick_x;

            if(gamepad1.a)
                ClawPosition += Claw_Speed;
            else if (gamepad1.y)
                ClawPosition -= Claw_Speed;
            else if(gamepad1.x)
                Armpos += Arm_Speed;
            else if (gamepad1.b)
                Armpos -= Arm_Speed;
            else if (Math.abs(rx) > 0) {
                robot.Left.setPower(rx);
                robot.Right.setPower(-rx);
            }
            else if ((gamepad1.right_bumper) && (Spinpos > 0))
              Spinpos -= Spin_Speed;
            else if (gamepad1.right_bumper)
                Spinpos += Spin_Speed;
            else {
                robot.Left.setPower(y);
                robot.Right.setPower(y);
            }

            robot.Spinner.setPower(Spinpos);

            robot.Arm.setPower(Armpos);

            //ClawPosition = Range.clip(ClawPosition, robot.Claw_Min, robot.Claw_Max);
            robot.Claw.setPosition(ClawPosition);

            //telemetry.addData("x","%.2f",x);
            telemetry.addData("y","%.2f", y);
            telemetry.addData("Arm", "%.2f", Armpos);
            telemetry.addData("Claw","%.2f", ClawPosition);
            telemetry.addData("rx", "%.2f", rx);
            telemetry.addData("Spin","%.2f", Spinpos);
            telemetry.update();

            sleep(50);
        }
    }
}


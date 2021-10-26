package design.petterns.status.test;

public class Client {
    public static void main(String[] args) {
        Lift lift = new Lift();
        lift.setState(ILift.RUNNING_STATE);//电梯是停止的
//        lift.setState(ILift.STOPPING_STATE);//电梯是停止的
        lift.open();//开门
        lift.close();//关门
        lift.run();//运行
        lift.stop();//停止
    }
}

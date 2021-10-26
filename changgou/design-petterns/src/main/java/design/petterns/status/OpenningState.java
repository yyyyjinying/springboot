package design.petterns.status;

public class OpenningState extends LiftState {
    // 开启当然可以关闭了，我就想测试一下电梯门开关功能
    public void open() {
        System.out.println("电梯门开启...");
    }

    public void close() {
        //状态修改
        super.context.setLiftState(Context.closeingState);
        //动作委托为CloseState来执行，也就是委托给了ClosingState子类执行这个动作
        super.context.getLiftState().close();
    }

    //电梯门不能开着就跑，这里什么也不做
    public void run() {

    }

    //开门状态已经是停止的了
    public void stop() {

    }
}

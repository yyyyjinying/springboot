package design.petterns.responsibility;

// 总经理
public class GeneralManager extends Handler {

    public GeneralManager() {
        //总经理处理7天以上的请假
        super(Handler.NUM_SEVEN);
    }

    @Override
    public void handleLeave(LeaveRequest leave) {
        System.out.println(leave.getName() + "请假" + leave.getNum() + "天," + leave.getContent() + "。");
        System.out.println("总经理审批：同意。");
    }
}

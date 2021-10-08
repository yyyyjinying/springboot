package design.principles.demo4;

/**
 * 接口隔离原则
 */
public class ItcastSafetyDoor implements AntiTheft, Fireproof {

    public void antiTheft() {
        System.out.println("防盗");
    }

    public void fireproof() {
        System.out.println("防水");
    }
}

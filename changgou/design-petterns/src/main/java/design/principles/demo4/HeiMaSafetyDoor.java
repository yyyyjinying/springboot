package design.principles.demo4;

/**
 * 接口隔离原则
 */
public class HeiMaSafetyDoor implements AntiTheft,Fireproof,Waterproof {

    public void antiTheft() {
        System.out.println("防盗");
    }

    public void fireproof() {
        System.out.println("防火");
    }

    public void waterproof() {
        System.out.println("防水");
    }
}

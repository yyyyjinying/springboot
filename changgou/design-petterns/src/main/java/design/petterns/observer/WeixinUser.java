package design.petterns.observer;

public class WeixinUser implements Observer {
    // 微信名
    private String name;

    public WeixinUser(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void update(String message) {
        System.out.println(name + "-" + message);
    }
}

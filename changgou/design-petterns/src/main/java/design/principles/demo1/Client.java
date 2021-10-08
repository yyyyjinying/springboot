package design.principles.demo1;

/**
 * 开闭原则测试
 * 测试搜狗输入法
 */
public class Client {
    public static void main(String[] args) {
        // 1.创建搜狗输入法对象
        SougouInput input = new SougouInput();
        // 2.创建皮肤
        DefaultSkin defaultSkin = new DefaultSkin();
        input.setSkin(defaultSkin);

        input.display();

    }
}

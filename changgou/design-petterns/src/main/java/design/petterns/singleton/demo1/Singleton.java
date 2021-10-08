package design.petterns.singleton.demo1;

/**
 * 饿汉模式
 * 静态变量创建类的对象
 * 缺点是：暂用不必要的内存
 */
public class Singleton {
    // 私有构造函数阻止外部创建对象
    private Singleton(){}

    // 方法一
//    private static Singleton instance = new Singleton();

    // 方法二 静态代码块方式
    private static Singleton instance;
    static {
        instance = new Singleton();
    }

    public static Singleton getInstance(){
        return instance;
    }

}

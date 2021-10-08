package design.petterns.singleton.demo5;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

// 通过反射破坏客户端类
public class Client {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // 获取字节码对象
        Class clazz = Singleton.class;

        //2。获取无参构造函数
        Constructor cons = clazz.getDeclaredConstructor();

        // 3.取消访问检查
        cons.setAccessible(true);

        // 4。创建Singleton对象
         Singleton s1 = (Singleton) cons.newInstance();
         Singleton s2 = (Singleton) cons.newInstance();

        System.out.println(s1 == s2);

    }
}

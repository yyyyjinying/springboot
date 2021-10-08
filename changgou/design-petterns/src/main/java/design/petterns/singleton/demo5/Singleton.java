package design.petterns.singleton.demo5;

import java.io.Serializable;

public class Singleton implements Serializable {
    private static boolean flag = false;
    private Singleton(){
        synchronized (Singleton.class){
            if(flag){
                throw new RuntimeException("只能创建一个对象");
            }

            flag = true;
        }

    };
    private static class SingletonHolder{
        private static final Singleton SINGLETON = new Singleton();

    }
    public static Singleton getInstance(){
        return SingletonHolder.SINGLETON;
    }
}

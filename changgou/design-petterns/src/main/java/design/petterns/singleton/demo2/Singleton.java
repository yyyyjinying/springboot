package design.petterns.singleton.demo2;

/**
 * 懒汉式
 */
public class Singleton {
    private Singleton(){};

//    private static volatile Singleton singleton;
    private static Singleton singleton;

    /**
     * 线程不安全
     * @return
     */
//    public static Singleton getSingleton(){
//        if(singleton == null){
//            singleton = new Singleton();
//        }
//        return singleton;
//    }

    /**
     * synchronized关键字
     * 执行效果特别低
     * @return
     */
//    public static synchronized Singleton getSingleton(){
//        if(singleton == null){
//            singleton = new Singleton();
//        }
//        return singleton;
//    }

    /**
     * 双重检查锁模式
     * 在多线程的情况下，可能出现空指针问题
     */
//    public static Singleton getSingleton(){
//        // 第一次判断，如果instance不为null，不进入抢锁阶段，直接返回实例
//        if (singleton == null) {
//            synchronized (Singleton.class){
//                if (singleton == null) {
//                    singleton = new Singleton();
//                }
//            }
//        }
//        return singleton;
//    }

    /**
     * 内部静态类实现单例
     * 第一次加载Singleton类时不会去初始化INSTANCE，只有第一次调用getInstance，虚拟机加载SingletonHolder
     * 并初始化INSTANCE，这样不仅能确保线程安全，也能保证 Singleton 类的唯一性。
     */
    private static class SinglentonHolder{
        private static final Singleton SINGLETON = new Singleton();
    }

    public static Singleton getSingleton(){
        return SinglentonHolder.SINGLETON;
    }


}

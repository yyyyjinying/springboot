package design.petterns.observer.test;

import java.util.Random;

public class Client {
    public static void main(String[] args) throws ClassNotFoundException {
        //创建小偷对象
        Thief t = new Thief("隔壁老王");
        //创建警察对象
        Policemen p = new Policemen("小李");
        //让警察盯着小偷
        t.addObserver(p);
        //小偷偷东西
        t.steal();

        /**
         * 通过对象 获取Class类型的实例
         * 通过包名获取对应的Class对象
         */
//        Thief t = new Thief("隔壁老王");
        System.out.println(t.getName());
        System.out.println(t.getClass().getName());

        Random random = new Random();
        Class<? extends Random> aClass = random.getClass();
        String name = aClass.getName();
        System.out.println(name);

        String className = "design.petterns.observer.test.Thief";
        Class<?> aClass1 = Class.forName(className);
        System.out.println(aClass1);

        Class<?>[] interfaces = aClass1.getInterfaces();
        System.out.println(interfaces);

//        Class randomClass = Random.class;
//        System.out.println(randomClass);
//
//        Class<Thief> thiefClass = Thief.class;
//        System.out.println(thiefClass);


//
//        Class<Integer> integerClass = int.class;
//        System.out.println(integerClass);

//        String name1 = Double[].class.getName();
//        System.out.println(name1);
//        String name2 = int[].class.getName();
//        System.out.println(name2);

    }
}

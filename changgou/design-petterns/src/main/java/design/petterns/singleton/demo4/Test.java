package design.petterns.singleton.demo4;

import java.io.*;

/**
 * 表明序列化和反序列化已经破坏了单例设计模式
 */
public class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        Test.writeObject2File();
        Singleton a1 = Test.readObjectFromFile();
        Singleton a2 = Test.readObjectFromFile();
        System.out.println(a1 == a2);


    }

    private static Singleton readObjectFromFile() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/Users/yyyyjinying/Desktop/java_code/log/a.txt"));
        Singleton instance = (Singleton) ois.readObject();
        return instance;

    }


    public static void writeObject2File() throws IOException {
        Singleton instance = Singleton.getInstance();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/Users/yyyyjinying/Desktop/java_code/log/a.txt"));
        oos.writeObject(instance);
    }
}
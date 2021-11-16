package core_java_base.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 *1。 如何查看任意对象的数据域名和类型；
 * 2。获取对应的Class对象
 * 3。通过Class对象调用getDeclaredFields
 */
public class Demo {
    public Demo() {
    }

    public static void main(String[] args) {

        String name;
        if (args.length > 0) name = args[0];
        else {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter class name (e.g. java.util.Date): ");
            name = in.next();
        }

        try {
            // print class name and superclass name (if != Object)
            Class cl = Class.forName(name);
            Class supercl = cl.getSuperclass();
            String modifiers = Modifier.toString(cl.getModifiers());
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print("class " + name);
            if (supercl != null && supercl != Object.class) System.out.print(" extends "
                    + supercl.getName());

            System.out.print("\n{\n");
            printConstructors(cl);
            System.out.println();
            printMethods(cl);
            System.out.println();
            printFilds(cl);
            System.out.println("}");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void printConstructors(Class cl) {
        Constructor[] constructors = cl.getDeclaredConstructors();
        for (Constructor constructor : constructors) {

            String name = constructor.getName();
            System.out.print("   ");

            String modifier = Modifier.toString(cl.getModifiers());
            if (modifier.length() > 0) System.out.print(modifier + " ");
            System.out.print(name + "(");

            Class[] parameterTypes = constructor.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                if (i > 0) System.out.print(", ");
                System.out.print(parameterTypes[i].getName());
            }
            System.out.print(");\n");
        }

    }

    public static void printMethods(Class cl) {
        // 返回这个类或接口的全部方法 不包括超类继承的方法
        Method[] methods = cl.getDeclaredMethods();
        for (Method method : methods) {
            String name = method.getName();
            Class<?> returnType = method.getReturnType();

            System.out.print("  ");
            String modifiers = Modifier.toString(method.getModifiers());
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print(returnType.getName() + " " + name + "(");

            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                if (i > 0) System.out.print(",");
                System.out.print(parameterTypes[i].getName());
            }
            System.out.print(");\n");

        }

    }

    public static void printFilds(Class cl) {
        Field[] declaredFields = cl.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            String name = declaredField.getName();
            Class<?> type = declaredField.getType();

            String modifiers = Modifier.toString(declaredField.getModifiers());

            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print(type.getName() + " " + name+";\n");

        }

    }
}

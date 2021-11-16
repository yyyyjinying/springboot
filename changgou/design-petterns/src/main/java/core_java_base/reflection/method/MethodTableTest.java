package core_java_base.reflection.method;

import java.lang.reflect.Method;

public class MethodTableTest {
    public static void main(String[] args) throws NoSuchMethodException {

        Method square = MethodTableTest.class.getMethod("square", double.class);
        printTable(1, 10, 10, square);

        Method sqrt = Math.class.getMethod("sqrt", double.class);
        printTable(1, 10, 10, sqrt);
    }


    /**
     * Returns the square of a number
     * @param x
     * @return
     */
    public static double square(double x) {
        return x * x;
    }

    public static void printTable(double from, double to, int n, Method f)
    {
        // print out the method as table header 1 10 10 => 9 /9
        System.out.println(f);

        double dx = (to - from) / (n - 1);

        for (double x = from; x <= to; x += dx)
        {
            try
            {
                double y = (Double) f.invoke(null, x);
                System.out.printf("%10.4f | %10.4f%n", x, y);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }


}

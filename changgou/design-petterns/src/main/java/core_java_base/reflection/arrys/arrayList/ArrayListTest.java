package core_java_base.reflection.arrys.arrayList;

import core_java_base.reflection.arrys.CopyOfTest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This program demonstrates the ArrayList class.
 *
 * @author Cay Horstmann
 * @version 1.11 2012-01-26
 */
public class ArrayListTest {
    public static void main(String[] args) {
        // fill the staff array list with three Employee objects
        ArrayList<Employee> staff = new ArrayList<>();

        staff.add(new Employee("Carl Cracker", 75000, 1987, 12, 15));
        staff.add(new Employee("Harry Hacker", 50000, 1989, 10, 1));
        staff.add(new Employee("Tony Tester", 40000, 1990, 3, 15));




//      List<Employee> aa = Arrays.copyOf(staff,3);
        int[] a = new int[]{1, 2, 3};
        int[] ints = Arrays.copyOf(a, 3);

        System.out.println(ints);


        // raise everyone's salary by 5%
        for (Employee e : staff)
            e.raiseSalary(5);

        // print out information about all Employee objects
        for (Employee e : staff)
            System.out.println("name=" + e.getName() + ",salary=" + e.getSalary() + ",hireDay="
                    + e.getHireDay());


    }
}
package core_java_base.reflection.objectAnalyzer;

import java.util.ArrayList;

public class ObjectAnalyzerTest {
    public static void main(String[] args) {
        ArrayList<Integer> squres = new ArrayList<Integer>();

        for (int i = 1; i <= 5; i++) {
            squres.add(i*i);
            
        }

        ObjectAnalyzer objAnalyzer = new ObjectAnalyzer();
        String s = objAnalyzer.toString(squres);
        System.out.println(s);

    }
}

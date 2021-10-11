package design.petterns.singleton.abstractdemo1;

public class Client {
    public static void main(String[] args) {
        Student student = new Student("zhao", "jin");
        String description = student.getDescription();
        String name = student.getName();
        System.out.println(description + " " + name);
    }
}

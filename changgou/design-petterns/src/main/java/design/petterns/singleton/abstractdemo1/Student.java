package design.petterns.singleton.abstractdemo1;

public class Student extends Preson {
    private String major;

    public Student(String name, String major) {
        super(name);
        this.major = major;

    }

    public String getDescription() {
        return "a student majoring in " + major;
    }

}

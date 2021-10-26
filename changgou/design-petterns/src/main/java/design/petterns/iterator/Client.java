package design.petterns.iterator;

public class Client {
    public static void main(String[] args) {
        StudentAggregateImpl studentAggregate = new StudentAggregateImpl();
        studentAggregate.addStudent(new Student("zhao",1));
        studentAggregate.addStudent(new Student("jin",2));
        studentAggregate.addStudent(new Student("ying",3));

        StudentIterator studentIterator = studentAggregate.getStudentIterator();

        while(studentIterator.hasNext()){
            Student next = studentIterator.next();
            System.out.println(next.toString());

        }
    }
}

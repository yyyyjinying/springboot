package design.petterns.iterator;

import java.util.ArrayList;
import java.util.List;

public class StudentAggregateImpl implements StudentAggregate {
    private List<Student> list = new ArrayList<Student>();  // 学生列表

    public void addStudent(Student student) {
        list.add(student);
    }

    public void removeStudent(Student student) {
        list.remove(student);
    }

    public StudentIterator getStudentIterator() {
        return new StudentIteratorImpl(list);
    }
}

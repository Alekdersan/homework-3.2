package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements StudentService{

    private Long generatedId = 0L;
    private final Map<Long, Student> students = new HashMap<>();


    @PostConstruct
    public void init() {
        creatStudent(new Student("Harry Potter", 30));
        creatStudent(new Student("Cedric Diggory", 45));
        creatStudent(new Student("Zhou Chang", 42));
        creatStudent(new Student("Graham Montague", 30));
    }

    @Override
    public Student creatStudent(Student student) {
        student.setId(++generatedId);
        students.put(generatedId, student);
        return student;
    }

    @Override
    public Student findStudentById(Long id) {
        return students.get(id);
    }

    @Override
    public Student editStudent(Student student) {
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return student;
        }
        return null;
    }

    @Override
    public Student deleteStudent(Long id) {
        return students.remove(id);
    }

    @Override
    public Collection<Student> getAllStudents() {
        return students.values();
    }

    public List<Student> getByAge(int age) {
        return students.values().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }

}

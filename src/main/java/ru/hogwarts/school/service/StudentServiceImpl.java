package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService{

    private final Map<Long, Student> students = new HashMap<>();
    private Long generatedId = 0L;


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



}

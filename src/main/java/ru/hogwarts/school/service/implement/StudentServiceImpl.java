package ru.hogwarts.school.service.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements StudentService {

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public Student creatStudent(Student student) {
        logger.info("Was invoked method for create student: {}", student);
        return studentRepository.save(student);
    }

    @Override
    public Student findStudentById(Long id) {
        logger.info("Finding for a student by ID = {}", id);
        return studentRepository.findById(id).get();
    }


    @Override
    public Student editStudent(Student student) {
        logger.debug("Editing a student: {}", student);
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        logger.debug("Removing a student by ID from the lists: {}", id);
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        logger.info("Get a list of all students");
        return studentRepository.findAll();
    }

    public Set<Student> getByAge(int age) {
        logger.info("Get the age of students = {}", age);
        return studentRepository.findAll().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toSet());
    }

    public Set<Student> findAllStudentsByAgeBetween(Integer min, Integer max) {
        logger.info("Find students by a given age between min = {} and max = {}", min, max);
        return studentRepository.findByAgeBetween(min, max);
    }

    public Double getAverageAgeOfAllStudents() {
        return studentRepository.findAll().stream().collect(Collectors.averagingInt(Student::getAge));
    }

    public Set<Student> findStudentsByNameContains(String part) {
        logger.info("Search for students by name part: {}", part);
        return studentRepository.findByNameContainsIgnoreCase(part);
    }

    @Override
    public List<String> getAllStudentsWithLetterA() {
        logger.info("Get a list of all students with liter A");
        return getAllStudents().stream()
                .parallel()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("A"))
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public void getStudentsThread() {

        List<Student> studentsThread = getAllStudents();

        System.out.println("1. " + studentsThread.get(0).getName());
        System.out.println("2. " + studentsThread.get(1).getName());

        new Thread(() -> {
            System.out.println("3. " + studentsThread.get(2).getName());
            System.out.println("4. " + studentsThread.get(3).getName());
        }).start();

        new Thread(() -> {
            System.out.println("5. " + studentsThread.get(4).getName());
            System.out.println("6. " + studentsThread.get(5).getName());
        }).start();
    }

    @Override
    public synchronized void run(int id) {
        String student = studentRepository.findAll().get(id).getName();
        System.out.println(id + ". " + student);
    }

    @Override
    public void printNameStudent() {
        run(0);
        run(1);

        new Thread(() -> {
            run(2);
            run(3);
        }).start();

        new Thread(() -> {
            run(4);
            run(5);
        }).start();
    }
}

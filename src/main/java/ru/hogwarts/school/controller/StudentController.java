package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Set;


@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Student student = studentService.findStudentById(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity getAllStudents(@RequestParam(required = false) String namePart) {
        if (namePart != null) {
            return ResponseEntity.ok(studentService.findStudentsByNameContains(namePart));
        }
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/filter/{age}")
    public Set<Student> getByAge(@PathVariable int age) {
        return studentService.getByAge(age);
    }

    @GetMapping(params = {"minAge", "maxAge"})
    public Set<Student> findAllStudentsByAgeBetween(
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge) {
        return studentService.findAllStudentsByAgeBetween(minAge, maxAge);
    }


    @PostMapping
    public ResponseEntity<Student> creatStudent(@RequestBody Student student) {
        if (student.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Student addStudent = studentService.creatStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(addStudent);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/faculty")
    public ResponseEntity<Faculty> getStudentOfFaculty(@PathVariable Long id) {
        Faculty student = studentService.findStudentById(id).getFaculty();
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }
}

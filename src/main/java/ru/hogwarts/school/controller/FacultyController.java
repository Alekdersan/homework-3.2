package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Set;


@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> getAllFaculties() {
        return ResponseEntity.ok(facultyService.getAllFaculties());
    }

    @GetMapping({"/bycolororname"})
    public ResponseEntity<Set<Faculty>> findFacultyByColorOrNameIgnoreCase(
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String title) {
        if (color != null || title != null) {
            return ResponseEntity.ok(facultyService.findFacultyByColorOrNameIgnoreCase(color, title));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/bycolor")
    public Set<Faculty> getByColor(@RequestParam(required = false) String color) {
        return facultyService.getByColor(color);
    }

    @PostMapping
    public ResponseEntity<Faculty> creatFaculty(@RequestBody Faculty faculty) {
        if (faculty.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Faculty addFaculty = facultyService.creatFaculty(faculty);
        return ResponseEntity.status(HttpStatus.CREATED).body(addFaculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/student")
    public ResponseEntity<Collection<Student>> getFacultyOfStudent(@PathVariable Long id) {
        Collection<Student> faculty = facultyService.findFaculty(id).getStudents();
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }
}
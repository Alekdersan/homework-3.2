package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.List;
import java.util.Set;


public interface FacultyService {

    Faculty creatFaculty(Faculty faculty);

    Faculty findFaculty(Long id);

    Faculty editFaculty(Faculty faculty);

    void deleteFaculty(Long id);

    Collection<Faculty> getAllFaculties();

    Set<Faculty> getByColor(String color);

    Set<Faculty> findFacultyByColorOrNameIgnoreCase(String color, String title);
}

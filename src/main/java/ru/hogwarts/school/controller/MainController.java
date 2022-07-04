package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("info")
@RestController
public class MainController {

        @GetMapping
        public ResponseEntity getInfoAboutAuthor() {
            return ResponseEntity.ok("Student of this faculty is Good person!");
        }

}

package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.services.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService service;

    public FacultyController(FacultyService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {
        Faculty faculty = service.findFaculty(id);
        if (faculty==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        Faculty newFaculty = service.createFaculty(faculty);
        return ResponseEntity.ok(newFaculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty foundFaculty = service.editFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);

    }

    @DeleteMapping("{id}")
    public Faculty deleteFaculty(@PathVariable Long id) {
        return service.deleteFaculty(id);

    }
    @GetMapping
    public ResponseEntity<Collection<Faculty>>getAllFaculties() {
        return ResponseEntity.ok(service.getAllFaculties());
    }

    @GetMapping("color/{color}")
    public ResponseEntity<Collection<Faculty>> getFacultiesByColor(@PathVariable String color) {
        Collection<Faculty> faculties = service.getFacultiesByColor(color);
        return ResponseEntity.ok(faculties);
    }
}

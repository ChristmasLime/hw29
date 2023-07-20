package ru.hogwarts.school.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Faculty;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class FacultyServiceTest {

    private FacultyService service;

    @BeforeEach
    void setUp() {
        service = new FacultyService();
    }

    @Test
    void createFacultyTest() {
        Faculty actual = service.createFaculty(new Faculty(null, "Gryffindor", "Yellow"));
        assertEquals("Gryffindor", actual.getName());
        assertEquals(1, actual.getId());
        assertEquals("Yellow", actual.getColor());
    }

    @Test
    void findFacultyTest() {
        service.createFaculty(new Faculty(null, "Slytherin", "Green"));
        service.createFaculty(new Faculty(null, "Ravenclaw", "Blue"));
        Faculty found = service.findFaculty(2);
        assertEquals(2, found.getId());
    }

    @Test
    void editFacultyTest() {
        Faculty createdFaculty = service.createFaculty(new Faculty(null, "Hufflepuff", "Black"));
        Faculty updatedFaculty = new Faculty(createdFaculty.getId(), "Slytherin", "Green");
        assertEquals("Slytherin", updatedFaculty.getName());
        assertEquals("Green", updatedFaculty.getColor());
        assertEquals(1, service.getAllFaculties().size());
    }

    @Test
    void deleteFacultyTest() {
        service.createFaculty(new Faculty(null, "Ravenclaw", "Blue"));
        service.deleteFaculty(1);
        assertEquals(0, service.getAllFaculties().size());
    }

    @Test
    void getAllFacultiesTest() {
        service.createFaculty(new Faculty(null, "Gryffindor", "Yellow"));
        service.createFaculty(new Faculty(null, "Slytherin", "Green"));
        service.createFaculty(new Faculty(null, "Ravenclaw", "Blue"));
        service.createFaculty(new Faculty(null, "Hufflepuff", "Black"));
        assertEquals(4, service.getAllFaculties().size());
    }

    @Test
    void getFacultiesByColor() {
        service.createFaculty(new Faculty(null, "Gryffindor", "Yellow"));
        service.createFaculty(new Faculty(null, "Slytherin", "Green"));
        service.createFaculty(new Faculty(null, "Ravenclaw", "Blue"));
        Collection<Faculty> facultiesByColor = service.getFacultiesByColor("Red");
        assertEquals(0, facultiesByColor.size());
    }

}

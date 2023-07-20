package ru.hogwarts.school.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class StudentServiceTest {

    private StudentService service;

    @BeforeEach
    void setUp() {
        service = new StudentService();
    }

    @Test
    void createStudentTest() {
        Student actual = service.createStudent(new Student(null, "Roman", 22));
        assertEquals("Roman", actual.getName());
        assertNotNull(actual.getId());
        assertEquals(22, actual.getAge());
    }

    @Test
    void findStudentTest() {
        service.createStudent(new Student(null, "John", 32));
        service.createStudent(new Student(null, "Sasha", 31));
        Student foundStudent = service.findStudent(2);
        assertEquals(2, foundStudent.getId());

    }

    @Test
    void editStudentTest() {
        Student createdStudent = service.createStudent(new Student(null, "Pasha", 27));
        Student updatedStudent = service.editStudent(new Student(createdStudent.getId(), "Roma", 66));
        assertNotNull(updatedStudent);
        assertEquals("Roma", updatedStudent.getName());
        assertEquals(1, service.getAllStudents().size());

    }

    @Test
    void deleteStudentTest() {
        service.createStudent(new Student(null, "Klara", 77));
        service.deleteStudent(1);
        assertEquals(0, service.getAllStudents().size());
    }

    @Test
    void getAllStudents() {
        service.createStudent(new Student(null, "Prohor", 14));
        service.createStudent(new Student(null, "John", 22));
        service.createStudent(new Student(null, "Draco", 17));
        assertEquals(3, service.getAllStudents().size());
    }

    @Test
    void getStudentsByAge() {
        service.createStudent(new Student(null, "Rita", 22));
        service.createStudent(new Student(null, "Bill", 22));
        service.createStudent(new Student(null, "Chigivara", 17));
        Collection<Student> getStudentsByAge = service.getStudentByAge(22);
        assertEquals(2, getStudentsByAge.size());
    }
}

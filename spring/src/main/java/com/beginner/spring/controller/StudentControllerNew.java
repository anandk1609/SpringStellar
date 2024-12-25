package com.beginner.spring.controller;

import com.beginner.spring.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentControllerNew {
    @GetMapping("student")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(
                1,
                "Sam",
                "Bahadur"
        );
        return ResponseEntity.ok()
                .header("custom-header", "Kony")
                .body(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Rakesh", "Sharma"));
        students.add(new Student(2, "Jaya", "Balan"));
        students.add(new Student(3, "Rick", "Sanchez"));
        students.add(new Student(4, "Morty", "Mortimer"));
        students.add(new Student(5, "Rachel", "Green"));
        return ResponseEntity.ok(students);
    }

    @GetMapping("students/{id}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId) {
        Student student = new Student(studentId, "Monica", "Bing");
        return ResponseEntity.ok(student);
    }

    //Spring BOOT REST API with Path Variable
    //{id}, {first-name}, {last-name} - URI Template variable
    // http://localhost:8080/students/1/admin/jojo
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariables(@PathVariable("id") int studentId,
                                                        @PathVariable("first-name") String firstName,
                                                        @PathVariable("last-name") String lastName) {
        Student student = new Student(studentId, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    // Spring BOOT REST API with Request Param
    // http://localhost:8080/students/query?id=1&firstName=admin&lastName=jojo
    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
                                                          @RequestParam String firstName,
                                                          @RequestParam String lastName) {
        Student student = new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    //Spring Boot REST API that handles HTTP POST Request
    // @PostMapping, @ResponseStatus and @ResponseBody
    @PostMapping("create")
    //First way of configuring http status
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int studentId) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId) {
        System.out.println(studentId);
        return ResponseEntity.ok("Student deleted successfully");
    }
}

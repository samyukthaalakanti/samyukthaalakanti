package com.student.crudactivity.restcontroller;

import com.student.crudactivity.entity.Student;
import com.student.crudactivity.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private StudentService studentService;

    public StudentRestController(StudentService theStudentService){
        studentService=theStudentService;
    }
    @GetMapping("/students")
    public List<Student> findAll(){
        return studentService.findAll();
    }


    @GetMapping("students/{studentId}")
    public Student findById(@PathVariable int studentId) {
        Student theStudent = studentService.findById(studentId);
        if (theStudent == null) {
            throw new RuntimeException("student id not found: -" + studentId);
        }
        return theStudent;
    }
@PostMapping("/students")
    public Student addStudent (@RequestBody Student theStudent) {
    theStudent.setId(0);
    studentService.save(theStudent);
    return theStudent;
    }
    @PutMapping("/students")
    public Student updateStudent(@RequestBody Student theStudent){
        studentService.save(theStudent);
        return theStudent;
    }
    @DeleteMapping("/students/{studentId}")
    public String deleteById(@PathVariable int studentId){
        Student tempStudent=studentService.findById(studentId);
        if(tempStudent==null){
            throw new RuntimeException("student not found with id :"+studentId);
        }

        studentService.deleteById(studentId);

        return "Deleted student record with id: "+studentId;
}
}

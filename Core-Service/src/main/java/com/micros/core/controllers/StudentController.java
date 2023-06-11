package com.micros.core.controllers;

import com.micros.core.dtos.StudentDto;
import com.micros.core.models.StudentModel;
import com.micros.core.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentController {
    final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public ResponseEntity<List<StudentModel>> getAllStudents(){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Object> saveStudent(@RequestBody @Valid StudentDto studentDto){
        try {
            if(studentService.existsPerson(studentDto.getPerson())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Person is already in use!");
            }
            StudentModel studentModel = studentDto.toModel();
            return ResponseEntity.status(HttpStatus.CREATED).body(studentService.save(studentModel));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable(value="id") UUID id){
        try {
            Optional<StudentModel> studentModelOptional = studentService.findById(id);
            if(!studentModelOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found!");
            }
            studentService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Student deleted successfully.");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}

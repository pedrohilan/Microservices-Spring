package com.micros.exam.controllers;

import com.micros.exam.dtos.ExamDto;
import com.micros.exam.models.ExamModel;
import com.micros.exam.services.ExamService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/exam")
public class ExamController {

    final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping()
    public ResponseEntity<List<ExamModel>> getAllCampus(){
        return ResponseEntity.status(HttpStatus.OK).body(examService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Object> saveCampus(@RequestBody @Valid ExamDto campusDto){
        try {
            if(examService.existsName(campusDto.getName())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Name is already in use!");
            }
            ExamModel ExamModel = campusDto.toModel();
            return ResponseEntity.status(HttpStatus.CREATED).body(examService.save(ExamModel));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCampus(@PathVariable(value="id") UUID id){
        try{
            Optional<ExamModel> ExamModelOptional = examService.findById(id);
            if(!ExamModelOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exam not found!");
            }
            examService.delete(ExamModelOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Exam deleted successfully.");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCampus(@PathVariable(value="id") UUID id, @RequestBody @Valid ExamDto campusDto){
        try{
            Optional<ExamModel> ExamModelOptional = examService.findById(id);
            if(!ExamModelOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exam not found!");
            }
            if (!campusDto.getName().equals(ExamModelOptional.get().getName())) {
                if(examService.existsName(campusDto.getName())){
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Name is already in use!");
                }
            }
            ExamModel ExamModel = campusDto.toModel();
            ExamModel.setId(ExamModelOptional.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body(examService.save(ExamModel));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}

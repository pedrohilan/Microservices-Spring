package com.micros.core.controllers;

import jakarta.validation.Valid;
import com.micros.core.dtos.SubjectDto;
import com.micros.core.models.SubjectModel;
import com.micros.core.services.ProfessorSubjectService;
import com.micros.core.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    final SubjectService subjectService;
    @Autowired
    final ProfessorSubjectService professorSubjectService;

    public SubjectController(SubjectService subjectService, ProfessorSubjectService professorSubjectService) {
        this.subjectService = subjectService;
        this.professorSubjectService = professorSubjectService;
    }

    @GetMapping()
    public ResponseEntity<List<SubjectModel>> getAllSubjects(){
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Object> saveSubject(@RequestBody @Valid SubjectDto subjectDto){
        try {
            if(subjectService.existsName(subjectDto.getName())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Name is already in use!");
            }
            SubjectModel subjectModel = subjectDto.toModel();
            return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.save(subjectModel));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSubject(@PathVariable(value="id") UUID id){
        try{
            Optional<SubjectModel> subjectModelOptional = subjectService.findById(id);
            if(!subjectModelOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Campus not found!");
            }
            if(professorSubjectService.existsSubject(subjectModelOptional.get())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Subject is already in use - ProfessorSubject!");
            }
            subjectService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Subject deleted successfully.");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSubject(@PathVariable(value="id") UUID id, @RequestBody @Valid SubjectDto subjectDto){
        try{
            Optional<SubjectModel> subjectModelOptional = subjectService.findById(id);
            if(!subjectModelOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subject not found!");
            }
            if (!subjectDto.getName().equals(subjectModelOptional.get().getName())) {
                if(subjectService.existsName(subjectDto.getName())){
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Name is already in use!");
                }
            }
            SubjectModel subjectModel = subjectDto.toModel();
            subjectModel.setId(subjectModelOptional.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body(subjectService.save(subjectModel));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}

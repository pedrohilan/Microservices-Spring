package com.micros.core.controllers;

import com.micros.core.models.ProfessorModel;
import com.micros.core.models.ProfessorSubjectModel;
import com.micros.core.models.SubjectModel;
import lit.unichristus.edu.br.core.models.*;
import com.micros.core.services.ProfessorService;
import com.micros.core.services.ProfessorSubjectService;
import com.micros.core.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/professorSubject")
public class ProfessorSubjectController {

    @Autowired
    final ProfessorSubjectService professorSubjectService;
    @Autowired
    final ProfessorService professorService;
    @Autowired
    final SubjectService subjectService;

    public ProfessorSubjectController(ProfessorSubjectService professorSubjectService, ProfessorService professorService, SubjectService subjectService) {
        this.professorSubjectService = professorSubjectService;
        this.professorService = professorService;
        this.subjectService = subjectService;
    }


    @GetMapping()
    public ResponseEntity<List<ProfessorSubjectModel>> getAllProfessorSubjects(){
        return ResponseEntity.status(HttpStatus.OK).body(professorSubjectService.findAll());
    }

    @PostMapping("/{professor_id}/{subject_id}")
    public ResponseEntity<Object> saveProfessorSubject(@PathVariable UUID professor_id, @PathVariable UUID subject_id){
        try {
            Optional<ProfessorModel> professorModelOptional = professorService.findById(professor_id);
            Optional<SubjectModel> subjectModelOptional = subjectService.findById(subject_id);
            if (professorModelOptional.isPresent() && subjectModelOptional.isPresent()){
                var professorSubjectModel = new ProfessorSubjectModel();
                professorSubjectModel.setProfessor(professorModelOptional.get());
                professorSubjectModel.setSubject(subjectModelOptional.get());
                return ResponseEntity.status(HttpStatus.CREATED).body(professorSubjectService.save(professorSubjectModel));
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor or/and Subject not found!");
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProgramCoordinator(@PathVariable(value="id") UUID id){
        try {
            Optional<ProfessorSubjectModel> professorSubjectModel = professorSubjectService.findById(id);
            if(!professorSubjectModel.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProfessorSubject not found!");
            }
            professorSubjectService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("ProfessorSubject deleted successfully.");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}

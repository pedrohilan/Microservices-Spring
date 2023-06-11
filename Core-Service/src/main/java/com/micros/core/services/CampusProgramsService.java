package com.micros.core.services;

import com.micros.core.repositories.CampusProgramsRepository;
import com.micros.core.models.CampusModel;
import com.micros.core.models.CampusProgramsModel;
import com.micros.core.models.ProgramModel;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampusProgramsService {
    final CampusProgramsRepository campusProgramsRepository;

    public CampusProgramsService(CampusProgramsRepository campusProgramsRepository) {
        this.campusProgramsRepository = campusProgramsRepository;
    }

    public List<CampusProgramsModel> findAll(){
        return campusProgramsRepository.findByIsDeletedFalse();
    }

    public boolean existsCampus(CampusModel campus) {
        return campusProgramsRepository.existsByCampus(campus);
    }

    public boolean existsProgram(ProgramModel program) {
        return campusProgramsRepository.existsByProgram(program);
    }

    @Transactional
    public Object save(CampusProgramsModel campusProgramsModel){
        return campusProgramsRepository.save(campusProgramsModel);
    }
}

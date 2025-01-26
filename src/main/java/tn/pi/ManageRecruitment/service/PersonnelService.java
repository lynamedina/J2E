package tn.pi.ManageRecruitment.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.pi.ManageRecruitment.model.Personnel;
import tn.pi.ManageRecruitment.repository.PersonnelRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonnelService {

    @Autowired
    private PersonnelRepository personnelRepository;

    public List<Personnel> getAllPersonnels() {
        return personnelRepository.findAll();
    }

    public Optional<Personnel> getPersonnelById(Long id) {
        return personnelRepository.findById(id);
    }

    public Personnel addPersonnel(Personnel personnel) {
        return personnelRepository.save(personnel);
    }

    public Personnel updatePersonnel(Long id, Personnel personnelDetails) {
        Optional<Personnel> existingPersonnel = personnelRepository.findById(id);
        if (existingPersonnel.isPresent()) {
            Personnel personnel = existingPersonnel.get();
            personnel.setFirstName(personnelDetails.getFirstName());
            personnel.setLastName(personnelDetails.getLastName());
            personnel.setEmail(personnelDetails.getEmail());
            personnel.setPhoneNumber(personnelDetails.getPhoneNumber());
            personnel.setCompetences(personnelDetails.getCompetences());
            personnel.setCv(personnelDetails.getCv());
            personnel.setDateOfBirth(personnelDetails.getDateOfBirth());
            personnel.setDateOfJoining(personnelDetails.getDateOfJoining());
            return personnelRepository.save(personnel);
        }
        return null;
    }

    public void deletePersonnel(Long id) {
        personnelRepository.deleteById(id);
    }
}
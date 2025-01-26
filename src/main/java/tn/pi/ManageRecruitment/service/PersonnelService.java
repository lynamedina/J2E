package tn.pi.ManageRecruitment.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.pi.ManageRecruitment.model.Personnel;
import tn.pi.ManageRecruitment.repository.PersonnelRepository;

import java.io.IOException;
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

    public Personnel saveCv(Long id, MultipartFile file) throws IOException {
        Personnel personnel = personnelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personnel not found"));

        // Vérification de la taille du fichier (exemple : max 10 Mo)
        if (file.getSize() > 10 * 1024 * 1024) {
            throw new RuntimeException("Le fichier est trop volumineux. Max 10MB autorisés.");
        }

        personnel.setCv(file.getBytes());
        return personnelRepository.save(personnel);
    }

}
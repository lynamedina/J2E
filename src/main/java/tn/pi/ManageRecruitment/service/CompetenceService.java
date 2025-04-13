package tn.pi.ManageRecruitment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.pi.ManageRecruitment.model.Competence;
import tn.pi.ManageRecruitment.repository.CompetenceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompetenceService {

    @Autowired
    private CompetenceRepository competenceRepository;

    public List<Competence> getAllCompetences() {
        return competenceRepository.findAll();
    }

    public List<Competence> getCompetencesByPersonnelId(Long personnelId) {
        return competenceRepository.findByPersonnelId(personnelId);
    }

    public Optional<Competence> getCompetenceById(Long id) {
        return competenceRepository.findById(id);
    }

    public Competence addCompetence(Competence competence) {
        return competenceRepository.save(competence);
    }

    public Competence updateCompetence(Long id, Competence details) {
        return competenceRepository.findById(id).map(c -> {
            c.setNom(details.getNom());
            c.setNiveau(details.getNiveau());
            return competenceRepository.save(c);
        }).orElse(null);
    }

    public void deleteCompetence(Long id) {
        competenceRepository.deleteById(id);
    }
}
package tn.pi.ManageRecruitment.service;

import tn.pi.ManageRecruitment.model.FichePoste;
import tn.pi.ManageRecruitment.model.Personnel;
import tn.pi.ManageRecruitment.repository.FichePosteRepository;
import tn.pi.ManageRecruitment.repository.PersonnelRepository;
import tn.pi.ManageRecruitment.dto.ConformiteResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConformiteService {

    @Autowired
    private PersonnelRepository personnelRepository;

    @Autowired
    private FichePosteRepository fichePosteRepository;

    public ConformiteResult verifierConformite(Long personnelId, Integer fichePosteId) {
        Personnel personnel = personnelRepository.findById(personnelId).orElse(null);
        FichePoste fichePoste = fichePosteRepository.findById(fichePosteId).orElse(null);

        if (personnel == null || fichePoste == null) {
            return new ConformiteResult(false, "Personnel ou Poste non trouvé.");
        }

        String[] competencesPoste = fichePoste.getCompetences().split(",");
        String[] competencesPersonnel = personnel.getCompetences().split(",");

        int matchCount = 0;
        for (String cp : competencesPoste) {
            for (String cper : competencesPersonnel) {
                if (cp.trim().equalsIgnoreCase(cper.trim())) {
                    matchCount++;
                    break;
                }
            }
        }

        boolean conforme = matchCount == competencesPoste.length;

        String message = conforme ? "Le personnel est conforme au poste." : "Le personnel ne possède pas toutes les compétences requises.";
        return new ConformiteResult(conforme, message);
    }
}
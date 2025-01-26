package tn.pi.ManageRecruitment.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.pi.ManageRecruitment.model.FichePoste;
import tn.pi.ManageRecruitment.repository.FichePosteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FichePosteService {
    private FichePosteRepository fichePosteRepository;

    @Autowired
    public FichePosteService(FichePosteRepository fichePosteRepository) {
        this.fichePosteRepository = fichePosteRepository;
    }

    public List<FichePoste> getAllFichesPoste() {
        return fichePosteRepository.findAll();
    }

    public Optional<FichePoste> getFichePosteById(Integer id) {
        return fichePosteRepository.findById(id);
    }

    public FichePoste addFichePoste(FichePoste fichePoste) {
        return fichePosteRepository.save(fichePoste);
    }

    public FichePoste updateFichePoste(Integer id, FichePoste fichePosteDetails) {
        return fichePosteRepository.findById(id).map(fichePoste -> {
            fichePoste.setTitle(fichePosteDetails.getTitle());
            fichePoste.setDescription(fichePosteDetails.getDescription());
            return fichePosteRepository.save(fichePoste);
        }).orElseThrow(() -> new RuntimeException("FichePoste not found"));
    }

    public void deleteFichePoste(Integer id) {
        fichePosteRepository.deleteById(id);
    }
}


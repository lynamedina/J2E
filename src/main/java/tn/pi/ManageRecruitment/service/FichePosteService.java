package tn.pi.ManageRecruitment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.pi.ManageRecruitment.model.FichePoste;
import tn.pi.ManageRecruitment.repository.FichePosteRepository;

import java.util.List;

@Service
public class FichePosteService {
    @Autowired
    private final FichePosteRepository fichePosteRepository;

    public FichePosteService(FichePosteRepository fichePosteRepository) {
        this.fichePosteRepository = fichePosteRepository;
    }

    public List<FichePoste> getAllFichesPoste() {
        return fichePosteRepository.findAll();
    }

    public FichePoste createFichePoste(FichePoste fichePoste) {

        return fichePosteRepository.save(fichePoste);
    }
}


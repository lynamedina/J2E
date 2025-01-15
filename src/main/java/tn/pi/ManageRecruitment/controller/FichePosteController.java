package tn.pi.ManageRecruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.pi.ManageRecruitment.model.FichePoste;
import tn.pi.ManageRecruitment.service.FichePosteService;

import java.util.List;

@RestController
@RequestMapping("/api/fiches-poste")
public class FichePosteController {
    @Autowired
    private FichePosteService fichePosteService;

    public FichePosteController(FichePosteService fichePosteService) {

        this.fichePosteService = fichePosteService;
    }

    @GetMapping
    public List<FichePoste> getAllFichesPoste() {
        return fichePosteService.getAllFichesPoste();
    }

    @PostMapping
    public FichePoste createFichePoste(@RequestBody FichePoste fichePoste) {
        return fichePosteService.createFichePoste(fichePoste);
    }
}

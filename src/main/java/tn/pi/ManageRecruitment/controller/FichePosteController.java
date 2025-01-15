package tn.pi.ManageRecruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.pi.ManageRecruitment.model.FichePoste;
import tn.pi.ManageRecruitment.service.FichePosteService;

import java.util.List;

@RestController
@RequestMapping("/api/fiches-poste")
public class FichePosteController {

    @Autowired
    private FichePosteService fichePosteService;

    @GetMapping
    public List<FichePoste> getAllFichesPoste() {
        return fichePosteService.getAllFichesPoste();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FichePoste> getFichePosteById(@PathVariable Integer id) {
        return fichePosteService.getFichePosteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public FichePoste addFichePoste(@RequestBody FichePoste fichePoste) {
        return fichePosteService.addFichePoste(fichePoste);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FichePoste> updateFichePoste(@PathVariable Integer id, @RequestBody FichePoste fichePosteDetails) {
        try {
            FichePoste updatedFichePoste = fichePosteService.updateFichePoste(id, fichePosteDetails);
            return ResponseEntity.ok(updatedFichePoste);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFichePoste(@PathVariable Integer id) {
        fichePosteService.deleteFichePoste(id);
        return ResponseEntity.noContent().build();
    }
}


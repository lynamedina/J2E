package tn.pi.ManageRecruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.pi.ManageRecruitment.model.Competence;
import tn.pi.ManageRecruitment.service.CompetenceService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/competences")
public class CompetenceController {

    @Autowired
    private CompetenceService competenceService;

    @GetMapping
    public List<Competence> getAll() {
        return competenceService.getAllCompetences();
    }

    @GetMapping("/personnel/{personnelId}")
    public List<Competence> getByPersonnel(@PathVariable Long personnelId) {
        return competenceService.getCompetencesByPersonnelId(personnelId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Competence> getById(@PathVariable Long id) {
        Optional<Competence> competence = competenceService.getCompetenceById(id);
        return competence.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Competence create(@RequestBody Competence competence) {
        return competenceService.addCompetence(competence);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Competence> update(@PathVariable Long id, @RequestBody Competence competence) {
        Competence updated = competenceService.updateCompetence(id, competence);
        if (updated != null)
            return ResponseEntity.ok(updated);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        competenceService.deleteCompetence(id);
        return ResponseEntity.noContent().build();
    }
}
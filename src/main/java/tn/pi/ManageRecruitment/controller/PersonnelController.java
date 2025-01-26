package tn.pi.ManageRecruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.pi.ManageRecruitment.model.Personnel;
import tn.pi.ManageRecruitment.service.PersonnelService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personnel")
public class PersonnelController {

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Personnel> addPersonnel(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("competences") String competences,
            @RequestParam("dateOfBirth") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth,
            @RequestParam("dateOfJoining") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfJoining,
            @RequestParam("cv") MultipartFile cvFile) {

        try {
            Personnel personnel = new Personnel();
            personnel.setFirstName(firstName);
            personnel.setLastName(lastName);
            personnel.setEmail(email);
            personnel.setPhoneNumber(phoneNumber);
            personnel.setCompetences(competences);
            personnel.setDateOfBirth(dateOfBirth);
            personnel.setDateOfJoining(dateOfJoining);

            // Sauvegarde du fichier sous forme de byte array
            personnel.setCv(cvFile.getBytes());

            Personnel savedPersonnel = personnelService.addPersonnel(personnel);
            return ResponseEntity.ok(savedPersonnel);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Autowired
    private PersonnelService personnelService;

    @GetMapping
    public List<Personnel> getAllPersonnels() {
        return personnelService.getAllPersonnels();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personnel> getPersonnelById(@PathVariable Long id) {
        return personnelService.getPersonnelById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Personnel addPersonnel(@RequestBody Personnel personnel) {
        return personnelService.addPersonnel(personnel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personnel> updatePersonnel(@PathVariable Long id, @RequestBody Personnel personnelDetails) {
        Personnel updatedPersonnel = personnelService.updatePersonnel(id, personnelDetails);
        return updatedPersonnel != null ? ResponseEntity.ok(updatedPersonnel) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonnel(@PathVariable Long id) {
        personnelService.deletePersonnel(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/cv")
    public ResponseEntity<byte[]> getCv(@PathVariable Long id) {
        System.out.println("Fetching CV for personnel ID: " + id);
        Optional<Personnel> personnel = personnelService.getPersonnelById(id);

        if (personnel.isPresent() && personnel.get().getCv() != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(personnel.get().getCv());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
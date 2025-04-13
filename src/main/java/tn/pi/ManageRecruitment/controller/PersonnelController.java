package tn.pi.ManageRecruitment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.pi.ManageRecruitment.model.Personnel;
import tn.pi.ManageRecruitment.repository.PersonnelRepository;
import tn.pi.ManageRecruitment.service.PersonnelService;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
//import java.net.http.HttpHeaders;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personnel")
public class PersonnelController {

    private final PersonnelService personnelService;

    @Autowired
    public PersonnelController(PersonnelService personnelService) {
        this.personnelService = personnelService;
    }

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

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Personnel> updatePersonnel(
            @PathVariable Long id,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("competences") String competences,
            @RequestParam("dateOfBirth") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth,
            @RequestParam("dateOfJoining") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfJoining,
            @RequestPart(value = "cv", required = false) MultipartFile cvFile
    ) throws IOException {
        Optional<Personnel> existing = personnelService.getPersonnelById(id);

        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Personnel personnel = existing.get();
        personnel.setFirstName(firstName);
        personnel.setLastName(lastName);
        personnel.setEmail(email);
        personnel.setPhoneNumber(phoneNumber);
        personnel.setCompetences(competences);
        personnel.setDateOfBirth(dateOfBirth);
        personnel.setDateOfJoining(dateOfJoining);

        if (cvFile != null && !cvFile.isEmpty()) {
            personnel.setCv(cvFile.getBytes());
        }

        Personnel updated = personnelService.addPersonnel(personnel); // save()
        return ResponseEntity.ok(updated);
    }

    // Méthode pour supprimer un personnel
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonnel(@PathVariable Long id) {
        if (personnelService.deletePersonnel(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/cv")
    public ResponseEntity<byte[]> getCv(@PathVariable Long id) {
        System.out.println("Fetching CV for personnel ID: " + id);
        Optional<Personnel> personnel = personnelService.getPersonnelById(id);

        if (personnel.isPresent() && personnel.get().getCv() != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    //.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"cv.pdf\"")
                    .body(personnel.get().getCv());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
package tn.pi.ManageRecruitment.controller;

import tn.pi.ManageRecruitment.dto.ConformiteResult;
import tn.pi.ManageRecruitment.service.ConformiteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/conformite")
@CrossOrigin("*")
public class ConformiteController {

    @Autowired
    private ConformiteService conformiteService;

    @GetMapping
    public ConformiteResult verifier(@RequestParam Long personnelId, @RequestParam Integer posteId) {
        return conformiteService.verifierConformite(personnelId, posteId);
    }
}
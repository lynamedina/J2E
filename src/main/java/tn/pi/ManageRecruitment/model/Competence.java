package tn.pi.ManageRecruitment.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Competence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @Enumerated(EnumType.STRING)
    private Niveau niveau;

    @ManyToOne
    @JoinColumn(name = "personnel_id")
    private Personnel personnel;

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public enum Niveau {
        DEBUTANT,
        INTERMEDIAIRE,
        EXPERT
    }



}
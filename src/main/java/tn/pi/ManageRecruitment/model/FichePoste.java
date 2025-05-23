package tn.pi.ManageRecruitment.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fiches_poste")
public class FichePoste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "competences", nullable = false, columnDefinition = "TEXT")
    private String competences;

    @Column(name = "experience", nullable = false, columnDefinition = "TEXT")
    private String experience;


    // Constructors
    public FichePoste() {
    }

    public FichePoste(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // toString() for debugging
    @Override
    public String toString() {
        return "FichePoste{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getCompetences() {
        return competences;
    }

    public String getExperience() {
        return experience;
    }


    public void setCompetences(String competences) {
        this.competences = competences;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

}
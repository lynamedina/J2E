package tn.pi.ManageRecruitment.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "personnel")
public class Personnel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Lob
    @Column(name = "cv", columnDefinition = "LONGBLOB")
    private byte[] cv;

    @Column(name = "competences")
    private String competences;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "date_of_joining")
    private LocalDate dateOfJoining;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public byte[] getCv() {
        return cv;
    }

    public void setCv(byte[] cv) {
        this.cv = cv;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getCompetences() {
        return competences;
    }

    public void setCompetences(String competences) {
        this.competences = competences;
    }


    @Transient
    public List<String> getCompetenceList() {
        if (competences == null || competences.trim().isEmpty()) {                 //Elle lit la chaîne competences (ex: "java,SpringBoot,HTML"),
            return new ArrayList<>();                                              //Elle la découpe par virgule,
        }                                                                          //Elle retourne une List<String> proprement nettoyée.

        return Arrays.stream(competences.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }
}

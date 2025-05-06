package com.project.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
public class Candidature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCandidature;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "etudiant_id")
    
    private Etudiant etudiant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "offre_id")
    
    private Offre offre;

    private String status = "PENDING"; // Optional: "Pending", "Accepted", "Rejected", etc.
    private LocalDateTime recruitmentDate; // 🆕 Champ ajouté

    // Constructor, getters, and setters

    public Candidature() {}

    public Candidature(Etudiant etudiant, Offre offre, String status) {
        this.etudiant = etudiant;
        this.offre = offre;
        this.status = status;
    }

    public Long getIdCandidature() {
        return idCandidature;
    }

    public void setIdCandidature(Long idCandidature) {
        this.idCandidature = idCandidature;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDateTime getRecruitmentDate() {
        return recruitmentDate;
    }

    public void setRecruitmentDate(LocalDateTime recruitmentDate) {
        this.recruitmentDate = recruitmentDate;
    }
}

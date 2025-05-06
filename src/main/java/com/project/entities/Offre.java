package com.project.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Offre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOffre;

    private String titre;
    private String localisation;
    private String exigences;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entrepreneur_id")
    private Entrepreneur entrepreneur;

    @Temporal(TemporalType.DATE)
    private Date date;
    
    @OneToMany(mappedBy = "offre", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<Candidature> candidatures = new ArrayList<>();


    public Offre(Long idOffre, String titre, String localisation, String exigences, String description,
			Entrepreneur entrepreneur, Date date) {
		super();
		this.idOffre = idOffre;
		this.titre = titre;
		this.localisation = localisation;
		this.exigences = exigences;
		this.description = description;
		this.entrepreneur = entrepreneur;
		this.date = date;
	}

	// Constructeur par défaut
    public Offre() {
    }

    // Getters et Setters

    public Long getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(Long idOffre) {
        this.idOffre = idOffre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getExigences() {
        return exigences;
    }

    public void setExigences(String exigences) {
        this.exigences = exigences;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Entrepreneur getEntrepreneur() {
        return entrepreneur;
    }

    public void setEntrepreneur(Entrepreneur entrepreneur) {
        this.entrepreneur = entrepreneur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public Long getEntrepreneurId() {
        return entrepreneur != null ? entrepreneur.getIdEntrepreneur() : null;
    }
    public List<Candidature> getCandidatures() {
        return candidatures;
    }

    public void setCandidatures(List<Candidature> candidatures) {
        this.candidatures = candidatures;
    }
}

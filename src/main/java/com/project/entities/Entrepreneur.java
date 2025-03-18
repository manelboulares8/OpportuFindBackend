package com.project.entities;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Entrepreneur implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEntrepreneur; // Changé de idEmployee à idEntrepreneur

    private String fullName;
    private String email;

    @Column(nullable = false)
    private String password;
    private String localisation;
    private String secteur;
    private String aboutUs;

    @Override
    public String toString() {
        return "Entrepreneur [idEntrepreneur=" + idEntrepreneur + ", fullName=" + fullName + ", email=" + email + ", password="
                + password + ", localisation=" + localisation + ", secteur=" + secteur + ", aboutUs=" + aboutUs + "]";
    }

    public long getIdEntrepreneur() { // Changé de getIdEmployee à getIdEntrepreneur
        return idEntrepreneur;
    }

    public void setIdEntrepreneur(long idEntrepreneur) { // Changé de setIdEmployee à setIdEntrepreneur
        this.idEntrepreneur = idEntrepreneur;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public String getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(String aboutUs) {
        this.aboutUs = aboutUs;
    }

    public Entrepreneur() {
        super();
    }
    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(() -> "ROLE_ENTREPRENEUR");
    }

    public Entrepreneur(String fullName, String email, String password, String localisation,
                       String secteur, String aboutUs) {
        super();
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.localisation = localisation;
        this.secteur = secteur;
        this.aboutUs = aboutUs;
    }
}
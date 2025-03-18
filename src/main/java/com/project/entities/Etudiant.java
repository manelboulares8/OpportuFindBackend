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
public class Etudiant implements UserDetails  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEtudiant;
    private String fullName;
    private String email;
    private String university;
    private String parcours;
    private String cvUrl;
    
    @Column(nullable = false)
    private String password;

    public Etudiant(String fullName, String email, String university, String parcours, String cvUrl,
                    String password) {
        super();
        this.fullName = fullName;
        this.email = email;
        this.university = university;
        this.parcours = parcours;
        this.cvUrl = cvUrl;
        this.password = password;
    }

    public Etudiant() {
        super();
    }

    public long getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(long idEtudiant) {
        this.idEtudiant = idEtudiant;
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

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getParcours() {
        return parcours;
    }

    public void setParcours(String parcours) {
        this.parcours = parcours;
    }

    public String getCvUrl() {
        return cvUrl;
    }

    public void setCvUrl(String cvUrl) {
        this.cvUrl = cvUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Etudiant [idEtudiant=" + idEtudiant + ", fullName=" + fullName + ", email=" + email + ", university="
                + university + ", parcours=" + parcours + ", cvUrl=" + cvUrl + ", password=" + password + "]";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(() -> "ROLE_ETUDIANT");
    }

	@Override
    public String getUsername() {
        return email;
    }
}

package com.project.services;

import java.util.List;

import com.project.entities.Etudiant;

public interface EtudiantServices {
    Etudiant saveEtudiant(Etudiant e);
    Etudiant updateEtudiant(Etudiant e);
    void deleteEtudiant(Etudiant e);
    void deleteEtudiantById(Long id);
    Etudiant getEtudiant(Long id);
    List<Etudiant> getAllEtudiants();
    List<Etudiant> findByFullName(String fullName);
    List<Etudiant> findByFullNameContains(String fullName);
    List<Etudiant> findByUniversity(String university);
    List<Etudiant> findByOrderByFullNameAsc();
}
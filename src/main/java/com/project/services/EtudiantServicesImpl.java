package com.project.services;

import java.util.List;

import com.project.entities.Etudiant;
import com.project.repositories.EtudiantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EtudiantServicesImpl implements EtudiantServices {
    @Autowired 
    private EtudiantRepository etudiantRepository;
    
    @Override
    public Etudiant saveEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    @Override
    public Etudiant updateEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    @Override
    public void deleteEtudiant(Etudiant e) {
        etudiantRepository.delete(e);
    }

    @Override
    public void deleteEtudiantById(Long id) {
        etudiantRepository.deleteById(id);
    }

    @Override
    public Etudiant getEtudiant(Long id) {
        return etudiantRepository.findById(id).orElse(null);
    }

    @Override
    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public List<Etudiant> findByFullName(String fullName) {
        return etudiantRepository.findByFullName(fullName);
    }

    @Override
    public List<Etudiant> findByFullNameContains(String fullName) {
        return etudiantRepository.findByFullNameContains(fullName);
    }

    @Override
    public List<Etudiant> findByUniversity(String university) {
        return etudiantRepository.findByUniversity(university);
    }

    @Override
    public List<Etudiant> findByOrderByFullNameAsc() {
        return etudiantRepository.findByOrderByFullNameAsc();
    }
    
    
}

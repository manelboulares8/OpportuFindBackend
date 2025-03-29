package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.Entrepreneur;
import com.project.repositories.EntrepreneurRepository;

@Service
public class EntrepreneurServices {
    
    @Autowired
    private EntrepreneurRepository entrepreneurRepository;
    
    public List<Entrepreneur> getAllEntrepreneurs() {
        return entrepreneurRepository.findAll();
    }
    
    public Entrepreneur getEntrepreneur(Long idEntrepreneur) {
        return entrepreneurRepository.findById(idEntrepreneur).orElse(null);
    }
    
    public Entrepreneur saveEntrepreneur(Entrepreneur entrepreneur) {
        return entrepreneurRepository.save(entrepreneur);
    }
    
    public Entrepreneur updateEntrepreneur(Entrepreneur entrepreneur) {
        return entrepreneurRepository.save(entrepreneur);
    }
    
    public void deleteEntrepreneurById(Long idEntrepreneur) {
        entrepreneurRepository.deleteById(idEntrepreneur);
    }
    
    public List<Entrepreneur> findByFullNameContains(String fullName) {
        return entrepreneurRepository.findByFullNameContains(fullName);
    }
}

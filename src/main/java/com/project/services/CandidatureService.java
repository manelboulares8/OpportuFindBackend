package com.project.services;

import com.project.entities.Candidature;
import com.project.repositories.CandidatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CandidatureService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private CandidatureRepository candidatureRepository;
    

    public List<Candidature> getAllCandidatures() {
        return candidatureRepository.findAll();
    }

    public Optional<Candidature> getCandidatureById(Long id) {
        return candidatureRepository.findById(id);
    }

    public Candidature saveCandidature(Candidature candidature) {
        return candidatureRepository.save(candidature);
    }

    public void deleteCandidature(Long id) {
        candidatureRepository.deleteById(id);
    }
/*
    public Candidature updateCandidatureStatus(Long id, String status, LocalDateTime recruitmentDateTime) {
        Optional<Candidature> optionalCandidature = candidatureRepository.findById(id);
        
        if (optionalCandidature.isPresent()) {
            Candidature candidature = optionalCandidature.get();
            candidature.setStatus(status);

            if ("ACCEPTED".equalsIgnoreCase(status)) {
                candidature.setRecruitmentDate(recruitmentDateTime != null ? recruitmentDateTime : LocalDateTime.now());
            } else {
                candidature.setRecruitmentDate(null); // facultatif : effacer si rejeté ou en attente
            }

            return candidatureRepository.save(candidature);
        } else {
            throw new RuntimeException("Candidature not found with id " + id);
        }
    }*/
    public Candidature updateCandidatureStatus(Long id, String status, LocalDateTime recruitmentDateTime) {
        Optional<Candidature> optionalCandidature = candidatureRepository.findById(id);

        if (optionalCandidature.isPresent()) {
            Candidature candidature = optionalCandidature.get();
            candidature.setStatus(status);

            if ("ACCEPTED".equalsIgnoreCase(status)) {
                candidature.setRecruitmentDate(recruitmentDateTime != null ? recruitmentDateTime : LocalDateTime.now());
            } else {
                candidature.setRecruitmentDate(null); // efface la date si non accepté
            }

            candidatureRepository.save(candidature);

            // Supposons que Candidature a une relation avec Offre et Candidat -> Utilisateur -> Email
            String offerName = candidature.getOffre().getTitre(); // ou getJobPost().getTitle() selon ton modèle
            String email = candidature.getEtudiant().getEmail();

            emailService.sendRecruitmentResult(
                email,
                status,
                candidature.getRecruitmentDate() != null ? candidature.getRecruitmentDate().toString() : "",
                offerName
            );

            return candidature;
        } else {
            throw new RuntimeException("Candidature not found with id " + id);
        }
    }



    public List<Candidature> getCandidaturesByEtudiantId(Long etudiantId) {
        return candidatureRepository.findCandidaturesByEtudiantId(etudiantId);
    }
    
    // 2. Récupérer les candidatures pour les offres d'un entrepreneur
    public List<Candidature> getCandidaturesByEntrepreneurId(Long entrepreneurId) {
        return candidatureRepository.findCandidaturesByEntrepreneurId(entrepreneurId);
    }
}

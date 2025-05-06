package com.project.opportufind;

import static org.junit.jupiter.api.Assertions.*;

import com.project.entities.Candidature;
import com.project.entities.Etudiant;
import com.project.entities.Entrepreneur;
import com.project.entities.Offre;
import com.project.repositories.CandidatureRepository;
import com.project.repositories.EntrepreneurRepository;
import com.project.repositories.EtudiantRepository;
import com.project.repositories.OffreRepository;
import com.project.services.CandidatureService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class CandidatureTests {

    @Autowired
    private CandidatureRepository candidatureRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;
    
    @Autowired
    private EntrepreneurRepository entrepreneurRepository;

    @Autowired
    private OffreRepository offreRepository;

    @Autowired
    private CandidatureService candidatureService;

    @Test
    void contextLoads() {
    }
/*
    @Test
    void testCreateCandidature() {
        Etudiant etudiant = new Etudiant("Test Etudiant", "etudiant@example.com", "ISET", "Informatique", "cv.pdf", "pass123");
        Etudiant savedEtudiant = etudiantRepository.save(etudiant);

        Entrepreneur entrepreneur = new Entrepreneur("Test Ent", "ent@test.com", "pass123", "Tunis", "IT", "About us");
        entrepreneur = entrepreneurRepository.save(entrepreneur); 
        
        Offre offre = new Offre();
        offre.setTitre("Stage en développement");
        offre.setDescription("Développement Java");
        offre.setLocalisation("Tunis");
        offre.setEntrepreneur(entrepreneur);
        offre.setDate(new Date());
        Offre savedOffre = offreRepository.save(offre);

        Candidature candidature = new Candidature(savedEtudiant, savedOffre, "PENDING");
        Candidature savedCandidature = candidatureService.saveCandidature(candidature);

        assertNotNull(savedCandidature.getIdCandidature(), "ID de la candidature doit être généré.");
        assertEquals("PENDING", savedCandidature.getStatus(), "Le statut devrait être 'PENDING'.");
        assertEquals(savedEtudiant.getIdEtudiant(), savedCandidature.getEtudiant().getIdEtudiant(), "Etudiant associé incorrect.");
        assertEquals(savedOffre.getIdOffre(), savedCandidature.getOffre().getIdOffre(), "Offre associée incorrecte.");
    }

    @Test
    void testFindCandidatureById() {

        Candidature found = candidatureService.getCandidatureById(1L).orElse(null);

        assertNotNull(found, "La candidature devrait être trouvée.");
        assertEquals("PENDING", found.getStatus(), "Le statut devrait être 'PENDING'.");
    }

    @Test
    void testUpdateCandidatureStatus() {
        Etudiant etudiant = new Etudiant("Sara C", "sara@gmail.com", "ENIT", "Génie Logiciel", "cvSara.pdf", "pass456");
        Etudiant savedEtudiant = etudiantRepository.save(etudiant);

        Entrepreneur entrepreneur = new Entrepreneur("Test Ent", "ent@test.com", "pass123", "Sousse", "IT", "About us");
        entrepreneur = entrepreneurRepository.save(entrepreneur);
        
        Offre offre = new Offre();
        offre.setTitre("Data Scientist");
        offre.setDescription("Python/ML");
        offre.setLocalisation("Sousse");
        offre.setEntrepreneur(entrepreneur);
        offre.setDate(new Date());
        Offre savedOffre = offreRepository.save(offre);

        Candidature candidature = new Candidature(savedEtudiant, savedOffre, "PENDING");
        Candidature savedCandidature = candidatureRepository.save(candidature);

        Candidature updates = savedCandidature;
        updates.setStatus("ACCEPTED");

        Candidature updated = candidatureService.updateCandidature(savedCandidature.getIdCandidature(), updates);

        assertEquals("ACCEPTED", updated.getStatus(), "Le statut devrait être 'ACCEPTED'.");
    }

    @Test
    void testDeleteCandidature() {

        candidatureService.deleteCandidature(2L);
    }*/
}
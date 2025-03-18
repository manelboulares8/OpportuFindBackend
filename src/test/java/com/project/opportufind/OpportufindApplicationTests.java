package com.project.opportufind;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.entities.Entrepreneur;
import com.project.entities.Etudiant;
import com.project.repositories.EntrepreneurRepository;
import com.project.repositories.EtudiantRepository;
import com.project.services.UserService; // Add this import

@SpringBootTest
class OpportufindApplicationTests {
    
    @Autowired
    EtudiantRepository etudiantRepository;
    
    @Autowired
    EntrepreneurRepository entrepreneurRepository;

    @Autowired
    UserService userService;  // Autowire the UserService

    @Test
    void contextLoads() {
    }

    @Test
    public void testCreateEtudiant() {
        Etudiant etu = new Etudiant("Boulares Manel", "mann2@gmail.com", "ISETN", "DSI", "url", "1234");
        
        // Use the UserService's register method to hash the password before saving
        Etudiant savedEtudiant = userService.registerEtudiant(etu);

        // Verifies if the ID is assigned (assuming the ID is auto-generated)
        assertNotNull(savedEtudiant.getIdEtudiant(), "L'ID de l'étudiant doit être généré.");
        
        // Verifies if the student is saved
        Etudiant foundEtudiant = etudiantRepository.findById(savedEtudiant.getIdEtudiant()).orElse(null);
        assertNotNull(foundEtudiant, "L'étudiant sauvegardé devrait être retrouvé.");
        assertEquals(etu.getFullName(), foundEtudiant.getFullName(), "Le nom de l'étudiant ne correspond pas.");
        
        // Check if the password is hashed
        assertNotEquals("1234", foundEtudiant.getPassword(), "Le mot de passe ne doit pas être en clair.");
        
        // After registration, test login and JWT generation
        String jwtToken = userService.login(etu.getEmail(), "1234");
        assertNotNull(jwtToken, "Le JWT devrait être généré après la connexion.");
        System.out.println("JWT Token for Etudiant: " + jwtToken);
    }

    @Test
    public void testCreateEntrepreneur() {
        Entrepreneur entrepreneur = new Entrepreneur("Ali Ben Salah", "ali2@gmail.com", "password123", "Tunis", "IT", "Startup innovante");
        
        // Use the UserService's register method to hash the password before saving
        Entrepreneur savedEntrepreneur = userService.registerEntrepreneur(entrepreneur);

        // Verifies if the ID is assigned
        assertNotNull(savedEntrepreneur.getIdEntrepreneur(), "L'ID de l'entrepreneur doit être généré.");

        // Verifies if the saved data matches
        Entrepreneur foundEntrepreneur = entrepreneurRepository.findById(savedEntrepreneur.getIdEntrepreneur()).orElse(null);
        assertNotNull(foundEntrepreneur, "L'entrepreneur sauvegardé devrait être retrouvé.");
        assertEquals(entrepreneur.getFullName(), foundEntrepreneur.getFullName(), "Le nom de l'entrepreneur ne correspond pas.");
        assertEquals(entrepreneur.getEmail(), foundEntrepreneur.getEmail(), "L'email ne correspond pas.");
        assertEquals(entrepreneur.getLocalisation(), foundEntrepreneur.getLocalisation(), "La localisation ne correspond pas.");

        // Check if the password is hashed
        assertNotEquals("password123", foundEntrepreneur.getPassword(), "Le mot de passe ne doit pas être en clair.");
        
        // After registration, test login and JWT generation
        String jwtToken = userService.login(entrepreneur.getEmail(), "password123");
        assertNotNull(jwtToken, "Le JWT devrait être généré après la connexion.");
        System.out.println("JWT Token for Entrepreneur: " + jwtToken);
    }
}

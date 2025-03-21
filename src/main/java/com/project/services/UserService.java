package com.project.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.project.repositories.EtudiantRepository;
import com.project.repositories.EntrepreneurRepository;
import com.project.entities.Entrepreneur;
import com.project.entities.Etudiant;
import com.project.services.JwtService;  // Import JwtService

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final EtudiantRepository etudiantRepository;
    private final EntrepreneurRepository entrepreneurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService; // Inject JwtService

    public UserService(EtudiantRepository etudiantRepository, 
                       EntrepreneurRepository entrepreneurRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) { // Inject JwtService here
        this.etudiantRepository = etudiantRepository;
        this.entrepreneurRepository = entrepreneurRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;  // Initialize JwtService
    }

    // Register Etudiant
    public Etudiant registerEtudiant(Etudiant etudiant) {
        // Check if email already exists
        if (etudiantRepository.findByEmail(etudiant.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already in use");
        }
        
        etudiant.setPassword(passwordEncoder.encode(etudiant.getPassword())); // Hash password
        return etudiantRepository.save(etudiant);
    }

    // Register Entrepreneur
    public Entrepreneur registerEntrepreneur(Entrepreneur entrepreneur) {
        // Check if email already exists
        if (entrepreneurRepository.findByEmail(entrepreneur.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already in use");
        }
        
        entrepreneur.setPassword(passwordEncoder.encode(entrepreneur.getPassword())); // Hash password
        return entrepreneurRepository.save(entrepreneur);
    }

    // Login logic
    public String login(String email, String password) {
        // Load user by email
        UserDetails userDetails = loadUserByUsername(email);

        // Verify password using PasswordEncoder
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            // Determine the role of the user (Etudiant or Entrepreneur)
            String role = userDetails instanceof Etudiant ? "ROLE_ETUDIANT" : "ROLE_ENTREPRENEUR";
            
            // If valid, generate JWT token
            return jwtService.generateToken(userDetails.getUsername(), role); // Pass email and role
        } else {
            throw new UsernameNotFoundException("Invalid credentials");
        }
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Etudiant> etudiant = etudiantRepository.findByEmail(email);
        if (etudiant.isPresent()) {
            return etudiant.get();
        }

        Optional<Entrepreneur> entrepreneur = entrepreneurRepository.findByEmail(email);
        if (entrepreneur.isPresent()) {
            return entrepreneur.get();
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}

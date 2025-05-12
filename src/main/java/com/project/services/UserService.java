package com.project.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.project.repositories.EtudiantRepository;
import com.project.repositories.EntrepreneurRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.entities.Entrepreneur;
import com.project.entities.Etudiant;
import com.project.services.JwtService;  // Import JwtService

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
            String role;
            long userId;

            if (userDetails instanceof Etudiant) {
                role = "ROLE_ETUDIANT";
                userId = ((Etudiant) userDetails).getIdEtudiant(); // Get ID from Etudiant
            } else if (userDetails instanceof Entrepreneur) {
                role = "ROLE_ENTREPRENEUR";
                userId = ((Entrepreneur) userDetails).getIdEntrepreneur(); // Get ID from Entrepreneur
            } else {
                throw new UsernameNotFoundException("User not found with email: " + email);
            }

            // If valid, generate JWT token with email, role, and id
            return jwtService.generateToken(userDetails.getUsername(), role, userId); // Pass email, role, and id
        } else {
            throw new UsernameNotFoundException("Invalid credentials");
        }
    }


    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Check if the user is an Etudiant
        Optional<Etudiant> etudiant = etudiantRepository.findByEmail(email);
        if (etudiant.isPresent()) {
            Etudiant foundEtudiant = etudiant.get();
            // You can access the ID here
            long userId = foundEtudiant.getIdEtudiant(); // Assuming Etudiant has getId() method
            // You can then use the userId as needed, or pass it along with the userDetails
            return foundEtudiant;
        }

        // Check if the user is an Entrepreneur
        Optional<Entrepreneur> entrepreneur = entrepreneurRepository.findByEmail(email);
        if (entrepreneur.isPresent()) {
            Entrepreneur foundEntrepreneur = entrepreneur.get();
            // Access the ID of the entrepreneur
            long userId = foundEntrepreneur.getIdEntrepreneur(); // Assuming Entrepreneur has getId() method
            // You can then use the userId as needed, or pass it along with the userDetails
            return foundEntrepreneur;
        }

        // If no user was found, throw exception
        throw new UsernameNotFoundException("User not found with email: " + email);
    }
    public Etudiant registerEtudiantWithCv(String etudiantJson, MultipartFile file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Etudiant etudiant = objectMapper.readValue(etudiantJson, Etudiant.class);

        if (etudiantRepository.findByEmail(etudiant.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already in use");
        }

        etudiant.setPassword(passwordEncoder.encode(etudiant.getPassword()));
        Etudiant savedEtudiant = etudiantRepository.save(etudiant);

        if (!file.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
            Path uploadPath = Paths.get("uploads");
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            savedEtudiant.setCvUrl(fileName);
            savedEtudiant = etudiantRepository.save(savedEtudiant);  // Save again with file info
        }

        return savedEtudiant;
    }


}

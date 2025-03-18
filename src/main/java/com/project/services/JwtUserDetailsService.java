package com.project.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.project.repositories.EtudiantRepository;
import com.project.repositories.EntrepreneurRepository;
import com.project.entities.Etudiant;
import com.project.entities.Entrepreneur;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final EtudiantRepository etudiantRepository;
    private final EntrepreneurRepository entrepreneurRepository;

    public JwtUserDetailsService(EtudiantRepository etudiantRepository, EntrepreneurRepository entrepreneurRepository) {
        this.etudiantRepository = etudiantRepository;
        this.entrepreneurRepository = entrepreneurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Etudiant etudiant = etudiantRepository.findByEmail(email).orElse(null);
        if (etudiant != null) {
            return etudiant;
        }

        Entrepreneur entrepreneur = entrepreneurRepository.findByEmail(email).orElse(null);
        if (entrepreneur != null) {
            return entrepreneur;
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}

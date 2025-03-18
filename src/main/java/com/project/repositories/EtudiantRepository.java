package com.project.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entities.Etudiant;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    Optional<Etudiant> findByEmail(String email);
    List<Etudiant> findByFullName(String fullName);
    List<Etudiant> findByFullNameContains(String fullName);
    List<Etudiant> findByUniversity(String university);
    List<Etudiant> findByOrderByFullNameAsc();
    
}

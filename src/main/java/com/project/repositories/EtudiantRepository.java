package com.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entities.Etudiant;

//@RepositoryRestResource(path = "rest")
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    
    List<Etudiant> findByFullName(String fullName);
    List<Etudiant> findByFullNameContains(String fullName);
    List<Etudiant> findByUniversity(String university);
    List<Etudiant> findByOrderByFullNameAsc();
    
}

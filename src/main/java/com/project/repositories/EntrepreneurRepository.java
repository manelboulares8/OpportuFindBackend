package com.project.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entities.Entrepreneur;

//@RepositoryRestResource(path = "rest-entrepreneurs")
public interface EntrepreneurRepository extends JpaRepository<Entrepreneur, Long> {
    
    List<Entrepreneur> findByFullName(String fullName);
    List<Entrepreneur> findByFullNameContains(String fullName);
    List<Entrepreneur> findByEmail(String email);
    List<Entrepreneur> findByLocalisation(String localisation);
    List<Entrepreneur> findBySecteur(String secteur);
    List<Entrepreneur> findByOrderByFullNameAsc();
}

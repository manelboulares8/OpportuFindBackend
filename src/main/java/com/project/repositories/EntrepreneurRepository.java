package com.project.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entities.Entrepreneur;

@Repository
public interface EntrepreneurRepository extends JpaRepository<Entrepreneur, Long> {
	Optional<Entrepreneur> findByEmail(String email);
    List<Entrepreneur> findByFullName(String fullName);
    List<Entrepreneur> findByFullNameContains(String fullName);
    List<Entrepreneur> findByLocalisation(String localisation);
    List<Entrepreneur> findBySecteur(String secteur);
    List<Entrepreneur> findByOrderByFullNameAsc();
}

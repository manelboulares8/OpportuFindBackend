package com.project.repositories;

import com.project.entities.Candidature;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatureRepository extends JpaRepository<Candidature, Long> {
	 @Query("SELECT c FROM Candidature c JOIN c.offre o WHERE o.entrepreneur.idEntrepreneur = :entrepreneurId")
	    List<Candidature> findCandidaturesByEntrepreneurId(@Param("entrepreneurId") Long entrepreneurId);  
	 @Query("SELECT c FROM Candidature c WHERE c.etudiant.idEtudiant = :etudiantId")
	    List<Candidature> findCandidaturesByEtudiantId(@Param("etudiantId") Long etudiantId);
	 
	 @Query("SELECT COUNT(c) FROM Candidature c WHERE c.status = :status")
	    long countByStatus(@Param("status") String status);
	    
	    @Query("SELECT c.offre.entrepreneur.secteur, COUNT(c) FROM Candidature c GROUP BY c.offre.entrepreneur.secteur")
	    List<Object[]> countCandidaturesBySecteur();
	    
	    @Query("SELECT FUNCTION('DATE', c.recruitmentDate), COUNT(c) FROM Candidature c WHERE c.status = 'ACCEPTED' GROUP BY FUNCTION('DATE', c.recruitmentDate)")
	    List<Object[]> countAcceptedCandidaturesByDate();
}

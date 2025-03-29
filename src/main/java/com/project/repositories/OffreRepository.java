package com.project.repositories;

import com.project.entities.Offre;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OffreRepository extends JpaRepository<Offre, Long> {
    // Corrected the query and method name to match the parameter naming
    @Query("SELECT o FROM Offre o WHERE o.entrepreneur.idEntrepreneur = :id")
    public List<Offre> findByEntrepreneur_Id(Long id);
}

package com.project.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.project.repositories.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
@CrossOrigin(origins = "http://localhost:4200") // Adjust port if needed

@Service
public class AdminDashboardService {
    private final CandidatureRepository candidatureRepo;
    private final OffreRepository offreRepo;
    private final EtudiantRepository etudiantRepo;

    public AdminDashboardService(CandidatureRepository candidatureRepo, 
                              OffreRepository offreRepo,
                              EtudiantRepository etudiantRepo) {
        this.candidatureRepo = candidatureRepo;
        this.offreRepo = offreRepo;
        this.etudiantRepo = etudiantRepo;
    }

    public Map<String, Long> getStatsSummary() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("totalOffres", offreRepo.count());
        stats.put("totalCandidatures", candidatureRepo.count());
        stats.put("pendingCandidatures", candidatureRepo.countByStatus("PENDING"));
        stats.put("acceptedCandidatures", candidatureRepo.countByStatus("ACCEPTED"));
        stats.put("rejectedCandidatures", candidatureRepo.countByStatus("REJECTED"));
        return stats;
    }

    public Map<String, Long> getCandidaturesBySecteur() {
        return candidatureRepo.countCandidaturesBySecteur().stream()
            .collect(Collectors.toMap(
                arr -> (String) arr[0],
                arr -> (Long) arr[1]
            ));
    }

    public Map<LocalDate, Long> getRecruitmentTrend() {
        return candidatureRepo.countAcceptedCandidaturesByDate().stream()
            .collect(Collectors.toMap(
                arr -> LocalDate.parse(arr[0].toString()),
                arr -> (Long) arr[1]
            ));
    }
}
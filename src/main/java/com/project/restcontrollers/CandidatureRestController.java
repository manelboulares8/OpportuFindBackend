package com.project.restcontrollers;

import com.project.entities.Candidature;
import com.project.entities.Etudiant;
import com.project.entities.Offre;
import com.project.repositories.EtudiantRepository;
import com.project.repositories.OffreRepository;
import com.project.services.CandidatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/candidatures")
@CrossOrigin(origins = "http://localhost:4200") // allow only localhost:4200
public class CandidatureRestController {

    @Autowired
    private CandidatureService candidatureService;
    
    @Autowired
    private OffreRepository offreRepository;  // Inject the OffreRepository

    @Autowired
    private EtudiantRepository etudiantRepository;
    
    @GetMapping
    public List<Candidature> getAllCandidatures() {
        return candidatureService.getAllCandidatures();
    }

    @GetMapping("/{id}")
    public Optional<Candidature> getCandidatureById(@PathVariable Long id) {
        return candidatureService.getCandidatureById(id);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> createCandidature(@RequestBody Map<String, Long> payload) {
        try {
            Long idOffre = payload.get("idOffre");
            Long idEtudiant = payload.get("idEtudiant");

            Offre offre = offreRepository.findById(idOffre)
                    .orElseThrow(() -> new RuntimeException("Offre not found"));
            Etudiant etudiant = etudiantRepository.findById(idEtudiant)
                    .orElseThrow(() -> new RuntimeException("Etudiant not found"));

            Candidature candidature = new Candidature();
            candidature.setOffre(offre);
            candidature.setEtudiant(etudiant);
            candidature.setStatus("PENDING");
            
            Candidature saved = candidatureService.saveCandidature(candidature);
            
            // Return simple success response
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"success\": true, \"id\": " + saved.getIdCandidature() + "}");
                    
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"success\": false, \"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Candidature> updateCandidatureStatus(
            @PathVariable Long id,
            @RequestParam String status,
            @RequestParam(required = false) String recruitmentDate) {

        LocalDateTime recruitmentDateTime = null;
        if (recruitmentDate != null && !recruitmentDate.isEmpty()) {
            recruitmentDateTime = LocalDateTime.parse(recruitmentDate);
        }

        Candidature updated = candidatureService.updateCandidatureStatus(id, status, recruitmentDateTime);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public void deleteCandidature(@PathVariable Long id) {
        candidatureService.deleteCandidature(id);
    }
    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<List<Candidature>> getCandidaturesByEtudiant(@PathVariable Long etudiantId) {
        List<Candidature> candidatures = candidatureService.getCandidaturesByEtudiantId(etudiantId);
        return ResponseEntity.ok(candidatures);
    }
    
    // Pour les candidatures des offres d'un entrepreneur
    @GetMapping("/entrepreneur/{entrepreneurId}")
    public ResponseEntity<List<Candidature>> getCandidaturesByEntrepreneur(@PathVariable Long entrepreneurId) {
        List<Candidature> candidatures = candidatureService.getCandidaturesByEntrepreneurId(entrepreneurId);
        return ResponseEntity.ok(candidatures);
    }
}

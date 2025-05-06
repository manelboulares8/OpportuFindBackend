package com.project.restcontrollers;

import com.project.entities.Offre;
import com.project.services.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/offres")
@CrossOrigin(origins = "http://localhost:4200") // Your Angular app URL
public class OffreController {

    @Autowired
    private OffreService offreService;

    // Récupérer la liste des offres
    @GetMapping
    public List<Offre> getAllOffres() {
        return offreService.getAllOffres();
    }

    // Ajouter une nouvelle offre
    @PostMapping("/ajouter")
    public ResponseEntity<Offre> addOffre(@RequestBody Offre offre) {
        Offre createdOffre = offreService.addOffre(offre);
        return new ResponseEntity<>(createdOffre, HttpStatus.CREATED);
    }

    // Modifier une offre existante
    @PutMapping("/{id}")
    public ResponseEntity<Offre> updateOffre(@PathVariable Long id, @RequestBody Offre offreDetails) {
        try {
            Offre updatedOffre = offreService.updateOffre(id, offreDetails);
            return new ResponseEntity<>(updatedOffre, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // L'offre n'a pas été trouvée
        }
    }

    // Supprimer une offre
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffre(@PathVariable Long id) {
        try {
            offreService.deleteOffre(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // L'offre n'a pas été trouvée
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Offre> getOffreById(@PathVariable Long id) {
        Optional<Offre> offre = offreService.getOffreById(id);
        return offre.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @GetMapping("/entrepreneur/{entrepreneurId}")
    public ResponseEntity<List<Offre>> getOffresByEntrepreneurId(@PathVariable Long entrepreneurId) {
        List<Offre> offres = offreService.getOffresByEntrepreneurId(entrepreneurId);
        if (offres.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Si aucune offre n'est trouvée
        }
        return new ResponseEntity<>(offres, HttpStatus.OK);
    }
    @GetMapping("/displayEntrepreneurId/{idOffre}")
    public ResponseEntity<Long> displayEntrepreneurId(@PathVariable Long idOffre) {
        System.out.println("Getting entrepreneur ID for offre with ID: " + idOffre);
        Long entrepreneurId = offreService.getEntrepreneurId(idOffre); // Get the entrepreneur ID
        if (entrepreneurId != null) {
            return ResponseEntity.ok(entrepreneurId); // Return the entrepreneur ID if found
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 if not found
        }
    }


}

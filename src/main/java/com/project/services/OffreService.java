package com.project.services;

import com.project.entities.Offre;
import com.project.repositories.OffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OffreService {

    @Autowired
    private OffreRepository offreRepository;

    // Récupérer toutes les offres
    public List<Offre> getAllOffres() {
        return offreRepository.findAll();
    }

    // Ajouter une nouvelle offre
    public Offre addOffre(Offre offre) {
        return offreRepository.save(offre);
    }

    // Modifier une offre existante
    public Offre updateOffre(Long id, Offre offreDetails) {
        Optional<Offre> optionalOffre = offreRepository.findById(id);
        if (optionalOffre.isPresent()) {
            Offre updatedOffre = optionalOffre.get();
            updatedOffre.setTitre(offreDetails.getTitre());
            updatedOffre.setLocalisation(offreDetails.getLocalisation());
            updatedOffre.setExigences(offreDetails.getExigences());
            updatedOffre.setDescription(offreDetails.getDescription());
            updatedOffre.setDate(offreDetails.getDate());
            return offreRepository.save(updatedOffre);
        } else {
            throw new RuntimeException("Offre non trouvée avec l'ID: " + id);
        }
    }
    // Récupérer une offre par ID
    public Optional<Offre> getOffreById(Long id) {
        return offreRepository.findById(id);
    }

    // Supprimer une offre par ID
    public void deleteOffre(Long id) {
        // Vérifier si l'offre existe avant de la supprimer
        if (offreRepository.existsById(id)) {
            offreRepository.deleteById(id);
        } else {
            throw new RuntimeException("Offre non trouvée avec l'ID: " + id);
        }
    }
 // Récupérer les offres par entrepreneurId
    public List<Offre> getOffresByEntrepreneurId(Long entrepreneurId) {
        return offreRepository.findByEntrepreneur_Id(entrepreneurId);
    }
    public Long getEntrepreneurId(Long idOffre) {
        // Fetch the offer by ID
        Offre offre = offreRepository.findById(idOffre).orElse(null);

        if (offre != null && offre.getEntrepreneur() != null) {
            return offre.getEntrepreneur().getIdEntrepreneur(); // Return the entrepreneur ID
        } else {
            return null; // Return null if no entrepreneur found
        }
    }


}

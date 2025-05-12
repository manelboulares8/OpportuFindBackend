package com.project.restcontrollers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.entities.Etudiant;
import com.project.services.EtudiantServices;

@RestController
@RequestMapping("/api/etudiants")
@CrossOrigin

public class EtudiantRestController {
	@Autowired
	EtudiantServices etudiantService;
	
	@RequestMapping(method=RequestMethod.GET)
	List <Etudiant> getAllEtudiants(){
		return etudiantService.getAllEtudiants();
		
		
	}
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public Etudiant getEtudiantById(@PathVariable("id") Long id) {
	return etudiantService.getEtudiant(id);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public Etudiant updateEtudiant(@RequestBody Etudiant etudiant) {
	return etudiantService.updateEtudiant(etudiant);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Etudiant createEtudiant(@RequestBody Etudiant etudiant) {
	return etudiantService.saveEtudiant(etudiant);
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public void deleteEtudiant(@PathVariable("id") Long id)
	{
	etudiantService.deleteEtudiantById(id);
	}
	
	@RequestMapping(value="/etud/{nom}",method = RequestMethod.GET)
	public List<Etudiant> findByNomContains(@PathVariable("nom") String nom) {
	return etudiantService.findByFullNameContains(nom);
	}
	@PostMapping("/{id}/upload-cv")
	public ResponseEntity<String> uploadCv(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
	    try {
	        Etudiant etudiant = etudiantService.getEtudiant(id);
	        if (etudiant == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found with id: " + id);
	        }

	        // Save file to server
	        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	        Path uploadPath = Paths.get("uploads");
	        if (!Files.exists(uploadPath)) {
	            Files.createDirectories(uploadPath);
	        }
	        Path filePath = uploadPath.resolve(fileName);
	        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

	        // Update cvUrl directly on the Etudiant entity
	        etudiant.setCvUrl(fileName); // Or filePath.toString() if full path needed
	        etudiantService.saveEtudiant(etudiant); // or updateEtudiant(etudiant)

	        return ResponseEntity.ok("CV uploaded successfully.");
	    } catch (IOException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading CV: " + e.getMessage());
	    }
	}
	@RequestMapping(method = RequestMethod.POST, consumes = {"multipart/form-data"})
	public ResponseEntity<?> createEtudiantWithCv(
	    @RequestParam("etudiant") String etudiantJson,
	    @RequestParam("file") MultipartFile file
	) {
	    try {
	        // Convert JSON string to Etudiant object
	        ObjectMapper objectMapper = new ObjectMapper();
	        Etudiant etudiant = objectMapper.readValue(etudiantJson, Etudiant.class);

	        // Save the Etudiant to get the ID
	        Etudiant savedEtudiant = etudiantService.saveEtudiant(etudiant);

	        // Validate and save file to server
	        if (file.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No file uploaded");
	        }

	        // Create a unique filename to avoid overwriting
	        String fileName = System.currentTimeMillis() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
	        Path uploadPath = Paths.get("uploads");  // You can use absolute path here if needed
	        if (!Files.exists(uploadPath)) {
	            Files.createDirectories(uploadPath);  // Make sure the directory exists
	        }

	        // Save the file
	        Path filePath = uploadPath.resolve(fileName);
	        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

	        // Update CV URL on Etudiant
	        savedEtudiant.setCvUrl(fileName);
	        etudiantService.saveEtudiant(savedEtudiant);  // Save the updated Etudiant

	        return ResponseEntity.ok(savedEtudiant);
	    } catch (IOException e) {
	        // Log the error for easier debugging
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
	    }
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = {"multipart/form-data"})
	public ResponseEntity<?> updateEtudiantWithCv(
	    @PathVariable Long id,
	    @RequestParam("etudiant") String etudiantJson,
	    @RequestParam("file") MultipartFile file
	) {
	    try {
	        ObjectMapper objectMapper = new ObjectMapper();
	        Etudiant etudiant = objectMapper.readValue(etudiantJson, Etudiant.class);
	        etudiant.setIdEtudiant(id); // Assure-toi que l’ID est bien mis

	        String fileName = System.currentTimeMillis() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
	        Path uploadPath = Paths.get("uploads");
	        if (!Files.exists(uploadPath)) {
	            Files.createDirectories(uploadPath);
	        }
	        Path filePath = uploadPath.resolve(fileName);
	        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

	        etudiant.setCvUrl(fileName);
	        Etudiant updatedEtudiant = etudiantService.updateEtudiant(etudiant);

	        return ResponseEntity.ok(updatedEtudiant);
	    } catch (IOException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur de mise à jour : " + e.getMessage());
	    }
	}


}

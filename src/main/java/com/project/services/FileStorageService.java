package com.project.services;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {
    private static final String UPLOAD_DIR = "uploads/cvs/";

    public String storeFile(MultipartFile file) throws IOException {
        // Créer le répertoire s'il n'existe pas
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Générer un nom de fichier unique
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        
        // Sauvegarder le fichier
        Files.copy(file.getInputStream(), filePath);
        
        // Retourner le chemin relatif (sans "uploads/")
        return "cvs/" + fileName;
    }

    public Path loadFile(String fileName) {
        return Paths.get(UPLOAD_DIR).resolve(fileName).normalize();
    }
}
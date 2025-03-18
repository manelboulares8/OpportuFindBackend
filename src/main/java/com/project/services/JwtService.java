package com.project.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.expiration}")
    private long EXPIRATION_TIME;

    /**
     * Génère un token JWT pour un utilisateur.
     *
     * @param email L'email de l'utilisateur (sujet du token).
     * @param role  Le rôle de l'utilisateur (ex: ROLE_ENTREPRENEUR, ROLE_ETUDIANT).
     * @return Le token JWT généré.
     */
    public String generateToken(String email, String role) {
        try {
            return JWT.create()
                    .withSubject(email) // L'email est le sujet du token
                    .withClaim("role", role) // Ajoute le rôle comme une "claim"
                    .withIssuedAt(new Date()) // Date de création du token
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Date d'expiration
                    .sign(Algorithm.HMAC256(SECRET_KEY)); // Signature avec la clé secrète
        } catch (Exception e) {
            throw new RuntimeException("Error generating JWT token", e);
        }
    }

    /**
     * Valide un token JWT et extrait l'email (sujet).
     *
     * @param token Le token JWT à valider.
     * @return L'email extrait du token.
     * @throws JWTVerificationException Si le token est invalide.
     */
    public String validateTokenAndExtractEmail(String token) throws JWTVerificationException {
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .build()
                    .verify(token); // Vérifie le token

            return decodedJWT.getSubject(); // Extrait l'email (sujet)
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Invalid or expired token", exception);
        }
    }

    /**
     * Extrait le rôle d'un token JWT.
     *
     * @param token Le token JWT.
     * @return Le rôle extrait du token.
     * @throws JWTVerificationException Si le token est invalide.
     */
    public String extractRole(String token) throws JWTVerificationException {
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .build()
                    .verify(token); // Vérifie le token

            return decodedJWT.getClaim("role").asString(); // Extrait le rôle
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Invalid or expired token", exception);
        }
    }
}

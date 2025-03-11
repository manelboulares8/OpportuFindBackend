package com.project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Etudiant {
	
	@Id
	private long idEtudiant;
	private String fullName;
	private String email;
	private String university;
	private String parcours;
	private String cvUrl;
	private String password;
	public Etudiant(int idEtudiant, String fullName, String email, String university, String parcours, String cvUrl,
			String password) {
		super();
		this.idEtudiant = idEtudiant;
		this.fullName = fullName;
		this.email = email;
		this.university = university;
		this.parcours = parcours;
		this.cvUrl = cvUrl;
		this.password = password;
	}
	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getIdEtudiant() {
		return idEtudiant;
	}
	public void setIdEtudiant(int idEtudiant) {
		this.idEtudiant = idEtudiant;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	public String getParcours() {
		return parcours;
	}
	public void setParcours(String parcours) {
		this.parcours = parcours;
	}
	public String getCvUrl() {
		return cvUrl;
	}
	public void setCvUrl(String cvUrl) {
		this.cvUrl = cvUrl;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Etudiant [idEtudiant=" + idEtudiant + ", fullName=" + fullName + ", email=" + email + ", university="
				+ university + ", parcours=" + parcours + ", cvUrl=" + cvUrl + ", password=" + password + "]";
	}
	
	

}

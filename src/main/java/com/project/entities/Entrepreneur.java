package com.project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Entrepreneur {
	@Id
	private long idEmployee;
	private String fullName;
	private String email;
	private String password;
	private String localisation;
	private String secteur;
	private String aboutUs;
	@Override
	public String toString() {
		return "Entrepreneur [idEmployee=" + idEmployee + ", fullName=" + fullName + ", email=" + email + ", password="
				+ password + ", localisation=" + localisation + ", secteur=" + secteur + ", aboutUs=" + aboutUs + "]";
	}
	public long getIdEmployee() {
		return idEmployee;
	}
	public void setIdEmployee(long idEmployee) {
		this.idEmployee = idEmployee;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLocalisation() {
		return localisation;
	}
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	public String getSecteur() {
		return secteur;
	}
	public void setSecteur(String secteur) {
		this.secteur = secteur;
	}
	public String getAboutUs() {
		return aboutUs;
	}
	public void setAboutUs(String aboutUs) {
		this.aboutUs = aboutUs;
	}
	public Entrepreneur() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Entrepreneur(long idEmployee, String fullName, String email, String password, String localisation,
			String secteur, String aboutUs) {
		super();
		this.idEmployee = idEmployee;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.localisation = localisation;
		this.secteur = secteur;
		this.aboutUs = aboutUs;
	}
	
	

}

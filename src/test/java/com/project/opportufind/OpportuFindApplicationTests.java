package com.project.opportufind;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.entities.Etudiant;
import com.project.repositories.EtudiantRepository;


@SpringBootTest
class OpportuFindApplicationTests {
    @Autowired

EtudiantRepository etudiantRepository;
	@Test
	void contextLoads() {
	}
	@Test
	public void testCreateEtudiant() {
		Etudiant etu=new Etudiant(1,"Boulares Manel","mann@gmail.com","ISETN","DSI","url","1234");
		etudiantRepository.save(etu);
		
	}
}

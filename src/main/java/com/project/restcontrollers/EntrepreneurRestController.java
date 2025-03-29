package com.project.restcontrollers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.entities.Entrepreneur;
import com.project.services.EntrepreneurServices;

@RestController
@RequestMapping("/api/entrepreneurs")
@CrossOrigin
public class EntrepreneurRestController {
    
    @Autowired
    EntrepreneurServices entrepreneurService;
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Entrepreneur> getAllEntrepreneurs() {
        return entrepreneurService.getAllEntrepreneurs();
    }
    
    @RequestMapping(value = "/{idEntrepreneur}", method = RequestMethod.GET)
    public Entrepreneur getEntrepreneurById(@PathVariable("idEntrepreneur") Long idEntrepreneur) {
        return entrepreneurService.getEntrepreneur(idEntrepreneur);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public Entrepreneur updateEntrepreneur(@RequestBody Entrepreneur entrepreneur) {
        return entrepreneurService.updateEntrepreneur(entrepreneur);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public Entrepreneur createEntrepreneur(@RequestBody Entrepreneur entrepreneur) {
        return entrepreneurService.saveEntrepreneur(entrepreneur);
    }
    
    @RequestMapping(value = "/{idEntrepreneur}", method = RequestMethod.DELETE)
    public void deleteEntrepreneur(@PathVariable("idEntrepreneur") Long idEntrepreneur) {
        entrepreneurService.deleteEntrepreneurById(idEntrepreneur);
    }
    
    @RequestMapping(value = "/search/{fullName}", method = RequestMethod.GET)
    public List<Entrepreneur> findByFullNameContains(@PathVariable("fullName") String fullName) {
        return entrepreneurService.findByFullNameContains(fullName);
    }
}

package com.inti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inti.model.Personne;
import com.inti.repository.IPersonneRepository;
import com.inti.service.PersonneServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("Personne")
public class PersonneController {

	@Autowired
	IPersonneRepository ipr;

	@Autowired
	PersonneServiceImpl psi;

	@PostMapping("/savePersonne")
	public void savePersonne(@RequestBody Personne pers) {
		if (pers != null) {
			psi.savePersonne(pers);
		}
		log.error("L'objet Personne " + pers + "n'a pas pu être enregistré en BDD.");
	}

	@GetMapping("/getPersonne/{id}")
	public Personne getPersonne(@PathVariable int id) {
		return psi.getPersonne(id);
	}

	@GetMapping("/getPersonnes")
	public List<Personne> getPersonnes() {
		return psi.getPersonnes();
	}

	@PostMapping("deletePersonne/{id}")
	public boolean deletePersonne(@PathVariable int id) {

		if (id > 0) {
			ipr.deleteById(id);
			return true;
		}
		return false;
	}
}

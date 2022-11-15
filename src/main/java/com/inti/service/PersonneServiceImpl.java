package com.inti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inti.model.Personne;
import com.inti.repository.IPersonneRepository;

@Service
public class PersonneServiceImpl implements IPersonneService {

	@Autowired
	IPersonneRepository ipr;

	@Override
	public List<Personne> getPersonnes() {
		List<Personne> lPersonne = ipr.findAll();
		return lPersonne;
	}

	@Override
	public Personne getPersonne(int id) {
		return ipr.findById(id).get();
	}

	@Override
	public void savePersonne(Personne pers) {
		ipr.save(pers);

	}

	@Override
	public void deletePersonne(int id) {
		ipr.delete(ipr.findById(id).get());

	}

}

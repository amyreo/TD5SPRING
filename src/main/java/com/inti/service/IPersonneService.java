package com.inti.service;

import java.util.List;

import com.inti.model.Personne;

public interface IPersonneService {

	public List<Personne> getPersonnes();

	public Personne getPersonne(int id);

	public void savePersonne(Personne pers);

	public void deletePersonne(int id);

}

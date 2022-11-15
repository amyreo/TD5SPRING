package com.inti;

import org.springframework.batch.item.ItemProcessor;

import com.inti.model.Personne;

public class PersonneItemProcessor implements ItemProcessor<Personne, Personne>{
	
	static int compteur = 0;

	@Override
	public Personne process(Personne pers) throws Exception {
		
		compteur++;
		pers.setId(compteur);
		
		String nom = pers.getNom();
		nom = nom.toUpperCase();
		pers.setNom(nom);

		String prenom = pers.getPrenom();
		prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1).toLowerCase();
		pers.setPrenom(prenom);
		
		return pers;
	}

}

package com.inti;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.inti.model.Personne;

public class PersonneItemWriter implements ItemWriter<Personne> {

	@Override
	public void write(List<? extends Personne> items) throws Exception {
		for (Personne personne : items) {
			System.out.println(personne);
		}
	}

}

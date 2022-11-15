package com.inti.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inti.model.Personne;

@Repository
@Transactional
public interface IPersonneRepository extends JpaRepository<Personne, Integer> {

}

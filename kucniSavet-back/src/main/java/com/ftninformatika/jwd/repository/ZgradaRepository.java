package com.ftninformatika.jwd.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.model.Zgrada;

@Repository
public interface ZgradaRepository extends JpaRepository<Zgrada,Long> {

	
	
	 
}

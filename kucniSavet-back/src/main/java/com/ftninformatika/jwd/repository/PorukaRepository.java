package com.ftninformatika.jwd.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.model.Poruka;


@Repository
public interface PorukaRepository extends JpaRepository<Poruka,Long> {
    
	List<Poruka> findByZgradaId(Long id);
	
	@Query("SELECT p FROM Poruka p WHERE "
			+ "(:zgradaId IS NULL OR p.zgrada.id = :zgradaId) AND " 
    		+ "(:naslov IS NULL OR p.naslov like %:naslov%) AND " 
    		+ "(:tip IS NULL OR p.tip like %:tip%)")
    		
	Page<Poruka> pretraga(@Param("zgradaId") Long zgradaId, @Param("naslov") String naslov, @Param("tip") String tip, Pageable pageable);
}


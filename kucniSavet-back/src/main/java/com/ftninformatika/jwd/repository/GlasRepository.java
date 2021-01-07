package com.ftninformatika.jwd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.model.Glas;

@Repository
public interface GlasRepository extends JpaRepository<Glas,Long> {
	
	List<Glas> findByPorukaId(Long id); 
}

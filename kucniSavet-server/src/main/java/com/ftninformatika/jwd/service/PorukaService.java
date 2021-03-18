package com.ftninformatika.jwd.service;

import com.ftninformatika.jwd.model.Poruka;
import com.ftninformatika.jwd.web.dto.PorukaDTO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

public interface PorukaService {
    
	Optional<Poruka> one(Long id);
	
	List<Poruka> findByZgradaId(Long id);
    
	Poruka save(PorukaDTO porukaDto);
    
	Poruka delete(Long id);
    
    Page<Poruka> findAll(int pageNum);
    
    Page<Poruka> pretraga(Long zgradaId, String naslov, String tip, int pageNum);

	//Poruka prenos(Long uplatiocId, Long primaocId, int iznos);



}

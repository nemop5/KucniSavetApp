package com.ftninformatika.jwd.service.impl;

import com.ftninformatika.jwd.model.Poruka;
import com.ftninformatika.jwd.repository.PorukaRepository;
import com.ftninformatika.jwd.service.PorukaService;
import com.ftninformatika.jwd.support.PorukaDtoToPoruka;
import com.ftninformatika.jwd.web.dto.PorukaDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaPorukaService implements PorukaService {

    @Autowired
    private PorukaRepository porukaRepository;
    
    @Autowired
    private PorukaDtoToPoruka toPoruka;

	@Override
	public Optional<Poruka> one(Long id) {
		return porukaRepository.findById(id);
	}

	@Override
	public Poruka save(PorukaDTO porukaDto) {
		Poruka poruka = toPoruka.convert(porukaDto);
		Poruka sacuvanaPoruka = porukaRepository.save(poruka);
		return sacuvanaPoruka;
	}

	@Override
	public Poruka delete(Long id) {
		Optional<Poruka> porukaOptional = porukaRepository.findById(id);
		if(porukaOptional.isPresent()) {
			Poruka poruka = porukaOptional.get();
			porukaRepository.deleteById(id);
			return poruka;
		}
		
		return null;
	}

	@Override
	public Page<Poruka> findAll(int pageNum) {
		return porukaRepository.findAll(PageRequest.of(pageNum, 5));
	}

	@Override
	public Page<Poruka> pretraga(Long zgradaId, String naslov, String tip, int pageNum) {
		return porukaRepository.pretraga(zgradaId, naslov, tip, PageRequest.of(pageNum, 5));
	}

	@Override
	public List<Poruka> findByZgradaId(Long id) {
		return porukaRepository.findByZgradaId(id);
	}

}

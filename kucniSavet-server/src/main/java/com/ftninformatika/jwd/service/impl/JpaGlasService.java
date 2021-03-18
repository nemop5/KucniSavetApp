package com.ftninformatika.jwd.service.impl;

import com.ftninformatika.jwd.model.Glas;
import com.ftninformatika.jwd.model.Poruka;
import com.ftninformatika.jwd.repository.GlasRepository;
import com.ftninformatika.jwd.repository.PorukaRepository;
import com.ftninformatika.jwd.service.GlasService;
import com.ftninformatika.jwd.support.GlasDtoToGlas;
import com.ftninformatika.jwd.web.dto.GlasDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaGlasService implements GlasService {
	
	@Autowired
	private GlasRepository glasRepository;
	
	@Autowired
	private PorukaRepository porukaRepository;
	
	@Autowired
	private GlasDtoToGlas toGlas;

	@Override
	public Optional<Glas> one(Long id) {
		return glasRepository.findById(id);
	}

	@Override
	public List<Glas> findAll() {
		return glasRepository.findAll();
	}
	
	@Override
	public List<Glas> findByPorukaId(Long id) {
		return glasRepository.findByPorukaId(id);
	}

	@Override
	public Glas save(GlasDTO glasDto) {
		Glas glas = toGlas.convert(glasDto);
		Long porukaId = glas.getPoruka().getId();
		
		List<Glas> sviGlasovi = glasRepository.findByPorukaId(porukaId);
		Poruka poruka = porukaRepository.getOne(porukaId);
		
		int za = 0;
		int ukBrojGlasova = 0;
		
		//Dobavljanje ukupnog broja glasova i glasova "ZA" iz baze
		for (Glas g: sviGlasovi) {
			ukBrojGlasova++;
			if (g.getPredlogPodrzan().equalsIgnoreCase("ZA")) {
				za++;
			}
		}
		//Racunat zabelezen trenutni glas
		ukBrojGlasova++;
		if (glas.getPredlogPodrzan().equalsIgnoreCase("ZA")) {
			za++;
		}
		
		int ukBrojStanara = glas.getPoruka().getZgrada().getBrojStanara();
		double procenat = ((double)za / (double)ukBrojStanara) * 100;
		
		double potrebanProcenat = glas.getPoruka().getPotrebanProcenat();
		if (procenat >= potrebanProcenat) {
			poruka.setPrihvacen(true);
		}
		
		System.out.println("*********************************************************************************************************************************");
		System.out.println("ZGRADA: " + glas.getPoruka().getZgrada().getAdresa());
		System.out.println("Uk broj stanara: " + ukBrojStanara);
		System.out.println("Uk broj glasova: " + ukBrojGlasova);
		System.out.println("Glasalo za: " + za);
		System.out.println("Procenat glasalo za: " + procenat);
		System.out.println("Potreban procenat: " + potrebanProcenat);
		System.out.println("*********************************************************************************************************************************");
		
		if (ukBrojGlasova <= ukBrojStanara) {
			porukaRepository.save(poruka);
			return glas;			
		} else {
			return null;
		}
	}
	
}

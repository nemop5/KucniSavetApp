package com.ftninformatika.jwd.support;

import com.ftninformatika.jwd.model.Poruka;
import com.ftninformatika.jwd.model.Zgrada;
import com.ftninformatika.jwd.service.PorukaService;
import com.ftninformatika.jwd.service.ZgradaService;
import com.ftninformatika.jwd.web.dto.PorukaDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PorukaDtoToPoruka implements Converter<PorukaDTO,Poruka> {

    @Autowired
    private PorukaService porukaService;
    
    @Autowired
    private ZgradaService zgradaService;

    @Override
    public Poruka convert(PorukaDTO porukaDto) {
		
		Zgrada zgrada = null;
		if(porukaDto.getZgradaId() != null) {
			zgrada = zgradaService.one(porukaDto.getZgradaId()).get();
		}
		
		if(zgrada!=null) {
			Long id = porukaDto.getId();
			Poruka poruka = id == null ? new Poruka() : porukaService.one(id).get();

			if(poruka != null) {
				poruka.setId(porukaDto.getId());
				poruka.setNaslov(porukaDto.getNaslov());
				poruka.setTip(porukaDto.getTip());
				poruka.setPotrebanProcenat(porukaDto.getPotrebanProcenat());
				poruka.setPrihvacen(porukaDto.isPrihvacen());
				poruka.setOpis(porukaDto.getOpis());
				
				poruka.setZgrada(zgrada);
			}
		
			return poruka;
		
		}else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
    }
    
}

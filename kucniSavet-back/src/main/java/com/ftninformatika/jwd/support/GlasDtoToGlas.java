package com.ftninformatika.jwd.support;

import com.ftninformatika.jwd.model.Glas;
import com.ftninformatika.jwd.model.Poruka;
import com.ftninformatika.jwd.service.GlasService;
import com.ftninformatika.jwd.service.PorukaService;
import com.ftninformatika.jwd.web.dto.GlasDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GlasDtoToGlas implements Converter<GlasDTO,Glas> {

	@Autowired
	private GlasService glasService;
	
    @Autowired
    private PorukaService porukaService;

    @Override
    public Glas convert(GlasDTO glasDto) {
		
		Poruka poruka = null;
		if(glasDto.getPorukaId() != null) {
			poruka = porukaService.one(glasDto.getPorukaId()).get();
		}
		
		if(poruka!=null) {
			Long id = glasDto.getId();
			Glas glas = id == null ? new Glas() : glasService.one(id).get();

			if(glas != null) {
				glas.setId(glasDto.getId());
				glas.setPredlogPodrzan(glasDto.getPredlogPodrzan());
				glas.setKomentar(glasDto.getKomentar());
				
				glas.setPoruka(poruka);
			}
		
			return glas;
		
		}else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
    }
    
}

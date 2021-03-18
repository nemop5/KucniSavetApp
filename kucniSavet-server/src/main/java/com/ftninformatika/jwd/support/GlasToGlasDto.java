package com.ftninformatika.jwd.support;
import com.ftninformatika.jwd.model.Glas;
import com.ftninformatika.jwd.web.dto.GlasDTO;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GlasToGlasDto implements Converter<Glas, GlasDTO> {
	
    @Override
    public GlasDTO convert(Glas glas) {
    	
        GlasDTO glasDto = new GlasDTO();
        
        glasDto.setId(glas.getId());
        glasDto.setPredlogPodrzan(glas.getPredlogPodrzan());
        glasDto.setKomentar(glas.getKomentar());
        
        glasDto.setPorukaId(glas.getPoruka().getId());
        glasDto.setPorukaNaslov(glas.getPoruka().getNaslov());
        return glasDto;
    }

    public List<GlasDTO> convert(List<Glas> glasovi){
        List<GlasDTO> glasoviDto = new ArrayList<>();

        for(Glas glas : glasovi) {
        	glasoviDto.add(convert(glas));
        }

        return glasoviDto;
    }
}

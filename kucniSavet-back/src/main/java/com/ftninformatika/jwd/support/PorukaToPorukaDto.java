package com.ftninformatika.jwd.support;

import com.ftninformatika.jwd.model.Poruka;
import com.ftninformatika.jwd.web.dto.PorukaDTO;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PorukaToPorukaDto implements Converter<Poruka, PorukaDTO> {

    @Override
    public PorukaDTO convert(Poruka poruka) {
    	
        PorukaDTO porukaDto = new PorukaDTO();
        
        porukaDto.setId(poruka.getId());
        porukaDto.setNaslov(poruka.getNaslov());
        porukaDto.setTip(poruka.getTip());
        porukaDto.setPotrebanProcenat(poruka.getPotrebanProcenat());
        porukaDto.setPrihvacen(poruka.isPrihvacen());
        
        porukaDto.setOpis(poruka.getOpis());
        
        porukaDto.setZgradaId(poruka.getZgrada().getId());
        porukaDto.setZgradaAdresa(poruka.getZgrada().getAdresa());
        
        return porukaDto;
    }

    public List<PorukaDTO> convert(List<Poruka> poruke){
        List<PorukaDTO> porukeDto = new ArrayList<>();

        for(Poruka poruka : poruke) {
        	porukeDto.add(convert(poruka));
        }

        return porukeDto;
    }

}


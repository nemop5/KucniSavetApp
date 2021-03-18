package com.ftninformatika.jwd.support;

import com.ftninformatika.jwd.model.Zgrada;
import com.ftninformatika.jwd.web.dto.ZgradaDTO;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ZgradaToZgradaDto implements Converter<Zgrada, ZgradaDTO> {

    @Override
    public ZgradaDTO convert(Zgrada zgrada) {
        ZgradaDTO zgradaDto = new ZgradaDTO();
        zgradaDto.setId(zgrada.getId());
        zgradaDto.setAdresa(zgrada.getAdresa());
        zgradaDto.setPredsednik(zgrada.getPredsednik());
        zgradaDto.setBrojStanova(zgrada.getBrojStanova());
        zgradaDto.setBrojStanara(zgrada.getBrojStanara());

        return zgradaDto;
    }

    public List<ZgradaDTO> convert(List<Zgrada> zgrade){
        List<ZgradaDTO> zgradeDto = new ArrayList<>();

        for(Zgrada zgrada : zgrade) {
        	zgradeDto.add(convert(zgrada));
        }

        return zgradeDto;
    }
}

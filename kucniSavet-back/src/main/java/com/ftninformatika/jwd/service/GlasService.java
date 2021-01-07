package com.ftninformatika.jwd.service;

import java.util.List;
import java.util.Optional;

import com.ftninformatika.jwd.model.Glas;
import com.ftninformatika.jwd.web.dto.GlasDTO;

public interface GlasService {

	Optional<Glas> one(Long id);

    List<Glas> findAll();

    Glas save(GlasDTO glasDto);
    
    List<Glas> findByPorukaId(Long id);

}

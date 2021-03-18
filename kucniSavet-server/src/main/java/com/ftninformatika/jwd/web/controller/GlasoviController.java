package com.ftninformatika.jwd.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.ftninformatika.jwd.model.Glas;
import com.ftninformatika.jwd.service.GlasService;
import com.ftninformatika.jwd.support.GlasToGlasDto;
import com.ftninformatika.jwd.web.dto.GlasDTO;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/glasovi", produces = MediaType.APPLICATION_JSON_VALUE)
public class GlasoviController {

    @Autowired
    private GlasService glasService;

    @Autowired
    private GlasToGlasDto toGlasDto;
    
    
    @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GlasDTO> create(@Valid @RequestBody GlasDTO glasDto){		

        Glas sacuvanGlas = glasService.save(glasDto);

        return new ResponseEntity<>(toGlasDto.convert(sacuvanGlas), HttpStatus.CREATED);
    }
    
}

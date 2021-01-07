package com.ftninformatika.jwd.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.ftninformatika.jwd.model.Poruka;
import com.ftninformatika.jwd.model.Zgrada;
import com.ftninformatika.jwd.service.PorukaService;
import com.ftninformatika.jwd.service.ZgradaService;
import com.ftninformatika.jwd.support.PorukaToPorukaDto;
import com.ftninformatika.jwd.support.ZgradaToZgradaDto;
import com.ftninformatika.jwd.web.dto.PorukaDTO;
import com.ftninformatika.jwd.web.dto.ZgradaDTO;

import java.util.List;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping(value = "/api/zgrade", produces = MediaType.APPLICATION_JSON_VALUE)
public class ZgradeController {

    @Autowired
    private ZgradaService zgradaService;

    @Autowired
    private ZgradaToZgradaDto toZgradaDto;
    
    @Autowired
    private PorukaService porukaService;
    
    @Autowired
    private PorukaToPorukaDto toPorukaDto;
    
    @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<ZgradaDTO>> getAll(){

        List<Zgrada> zgrade = zgradaService.findAll();

        return new ResponseEntity<>(toZgradaDto.convert(zgrade), HttpStatus.OK);
    }
    
    @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
    @GetMapping("/{id}/poruke")
    public ResponseEntity<List<PorukaDTO>> findByZgradaId(@PathVariable @Positive(message = "Id mora biti pozitivan.")  Long id){
        List<Poruka> poruke = porukaService.findByZgradaId(id);

        return new ResponseEntity<>(toPorukaDto.convert(poruke), HttpStatus.OK);
    }
}

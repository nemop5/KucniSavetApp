package com.ftninformatika.jwd.web.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ftninformatika.jwd.model.Poruka;
import com.ftninformatika.jwd.service.PorukaService;
import com.ftninformatika.jwd.support.PorukaToPorukaDto;
import com.ftninformatika.jwd.web.dto.PorukaDTO;

@RestController
@RequestMapping(value = "/api/poruke", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class PorukaController {

    @Autowired
    private PorukaService porukaService;
    
    @Autowired
    private PorukaToPorukaDto toPorukaDto;
    

    @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<PorukaDTO>> getAll(
    		@RequestParam(required=false) Long zgradaId,
            @RequestParam(required=false) String naslov,
            @RequestParam(required=false) String tip,
            @RequestParam(defaultValue = "0") int pageNum){

    	Page<Poruka> page = null;

		if (zgradaId != null || naslov != null || tip != null) {
			page = porukaService.pretraga(zgradaId, naslov, tip, pageNum);
		} else {
			page = porukaService.findAll(pageNum);
		}
        
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Total-Pages", Integer.toString(page.getTotalPages()));
        
        return new ResponseEntity<>(toPorukaDto.convert(page.getContent()), responseHeaders, HttpStatus.OK);
    }
    
    @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<PorukaDTO> getOne(@PathVariable Long id){
		Optional<Poruka> racun = porukaService.one(id);
		if (!racun.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toPorukaDto.convert(racun.get()), HttpStatus.OK);
	}
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PorukaDTO> create(@Valid @RequestBody PorukaDTO racunDto){		

        Poruka sacuvanRacun = porukaService.save(racunDto);

        return new ResponseEntity<>(toPorukaDto.convert(sacuvanRacun), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PorukaDTO> update(@PathVariable Long id, @Valid @RequestBody PorukaDTO racunDto){

        if(!id.equals(racunDto.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Poruka sacuvanRacun = porukaService.save(racunDto);

        return new ResponseEntity<>(toPorukaDto.convert(sacuvanRacun),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<PorukaDTO> delete(@PathVariable Long id){
    	
    	Poruka obrisanaPoruka = porukaService.delete(id);

        if(obrisanaPoruka == null) {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(toPorukaDto.convert(obrisanaPoruka),HttpStatus.OK);
    }
    

}

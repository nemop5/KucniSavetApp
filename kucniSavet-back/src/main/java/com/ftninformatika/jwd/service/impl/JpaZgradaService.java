package com.ftninformatika.jwd.service.impl;

import com.ftninformatika.jwd.model.Zgrada;
import com.ftninformatika.jwd.repository.ZgradaRepository;
import com.ftninformatika.jwd.service.ZgradaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaZgradaService implements ZgradaService {

    @Autowired
    private ZgradaRepository zgradaRepository;

	@Override
	public Optional<Zgrada> one(Long id) {
		return zgradaRepository.findById(id);
	}

	@Override
	public List<Zgrada> findAll() {
		return zgradaRepository.findAll();
	}

	@Override
	public Zgrada save(Zgrada zgrada) {
		return zgradaRepository.save(zgrada);
	}

    

}

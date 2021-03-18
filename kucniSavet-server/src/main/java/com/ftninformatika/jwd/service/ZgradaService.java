package com.ftninformatika.jwd.service;

import java.util.List;
import java.util.Optional;

import com.ftninformatika.jwd.model.Zgrada;

public interface ZgradaService {

	Optional<Zgrada> one(Long id);

    List<Zgrada> findAll();

    Zgrada save(Zgrada zgrada);
}

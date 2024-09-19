package com.example.franquicias.application.service;


import com.example.franquicias.domain.model.Franquicia;
import com.example.franquicias.domain.repository.FranquiciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FranquiciaService {
    private final FranquiciaRepository franquiciaRepository;

    public FranquiciaService(FranquiciaRepository franquiciaRepository) {
        this.franquiciaRepository = franquiciaRepository;
    }

    public Franquicia agregarFranquicia(String nombre) {
        Franquicia franquicia = new Franquicia(nombre);
        return franquiciaRepository.save(franquicia);
    }

    public List<Franquicia> listarFranquicias() {
        return franquiciaRepository.findAll();
    }
}


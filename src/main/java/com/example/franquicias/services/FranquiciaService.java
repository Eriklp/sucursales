package com.example.franquicias.services;

import com.example.franquicias.models.Franquicia;
import com.example.franquicias.repository.FranquiciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.franquicias.repository.FranquiciaRepository.*;

@Service
public class FranquiciaService {

    @Autowired
    private FranquiciaRepository franquiciaRepository;

    public Franquicia saveFranquicia(Franquicia Franquicia) {
        return franquiciaRepository.save(Franquicia);
    }

    public Optional<Franquicia> findById(Long id) {
        return franquiciaRepository.findById(id);
    }

    public void deleteFranquicia(Long id) {
        franquiciaRepository.deleteById(id);
    }
}

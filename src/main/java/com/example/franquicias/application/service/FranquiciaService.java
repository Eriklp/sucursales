package com.example.franquicias.application.service;


import com.example.franquicias.domain.model.Franquicia;
import com.example.franquicias.domain.repository.FranquiciaRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class FranquiciaService {
    private final FranquiciaRepository franquiciaRepository;

    public FranquiciaService(FranquiciaRepository franquiciaRepository) {
        this.franquiciaRepository = franquiciaRepository;
    }

    public Mono<Franquicia> agregarFranquicia(Franquicia franquicia) {
        return Mono.fromCallable(() -> franquiciaRepository.save(franquicia));
    }

    public Mono<Franquicia> actualizarNombreFranquicia(Long franquiciaId, String nuevoNombre) {
        return Mono.fromCallable(() -> franquiciaRepository.findById(franquiciaId))
                .flatMap(optionalFranquicia -> optionalFranquicia.map(franquicia -> {
                    franquicia.setNombre(nuevoNombre);
                    return Mono.just(franquiciaRepository.save(franquicia));
                }).orElseGet(Mono::empty));
    }

    public Flux<Franquicia> listarFranquicias() {
        return Flux.defer(() -> Flux.fromIterable(franquiciaRepository.findAll()));
    }
}


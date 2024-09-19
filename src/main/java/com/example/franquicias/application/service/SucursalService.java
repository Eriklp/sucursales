package com.example.franquicias.application.service;

import com.example.franquicias.domain.model.Franquicia;
import com.example.franquicias.domain.model.Sucursal;
import com.example.franquicias.domain.repository.FranquiciaRepository;
import com.example.franquicias.domain.repository.SucursalRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SucursalService {
    private final SucursalRepository sucursalRepository;
    private final FranquiciaRepository franquiciaRepository;

    public SucursalService(SucursalRepository sucursalRepository, FranquiciaRepository franquiciaRepository) {
        this.sucursalRepository = sucursalRepository;
        this.franquiciaRepository = franquiciaRepository;
    }

    public Mono<Sucursal> agregarSucursal(Long franquiciaId, Sucursal sucursal) {
        Franquicia franquicia = franquiciaRepository.findById(franquiciaId).orElseThrow();
        sucursal.setFranquicia(franquicia);
        return Mono.fromCallable(() -> sucursalRepository.save(sucursal));
    }
    public Flux<Sucursal> listarSucursalesPorFranquicia(Long franquiciaId) {
        return Flux.defer(() -> Flux.fromIterable(sucursalRepository.findByFranquiciaId(franquiciaId)));
    }
}

package com.example.franquicias.adapters.controller;

import com.example.franquicias.application.service.FranquiciaService;
import com.example.franquicias.domain.model.Franquicia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/franquicias")
public class FranquiciaController {

    @Autowired
    private FranquiciaService franquiciaService;

    @PostMapping
    public Mono<ResponseEntity<Franquicia>> agregarFranquicia(@RequestBody Franquicia franquicia) {
        return franquiciaService.agregarFranquicia(franquicia)
                .map(ResponseEntity::ok);
    }

    @GetMapping
    public Flux<Franquicia> listarFranquicias() {
        return franquiciaService.listarFranquicias();
    }
}

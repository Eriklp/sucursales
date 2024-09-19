package com.example.franquicias.adapters.controller;

import com.example.franquicias.application.service.FranquiciaService;
import com.example.franquicias.domain.model.Franquicia;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/franquicias")
public class FranquiciaController {
    private final FranquiciaService franquiciaService;

    public FranquiciaController(FranquiciaService franquiciaService) {
        this.franquiciaService = franquiciaService;
    }

    @PostMapping
    public ResponseEntity<Franquicia> agregarFranquicia(@RequestBody Franquicia franquicia) {
        Franquicia nuevaFranquicia = franquiciaService.agregarFranquicia(franquicia.getNombre());
        return new ResponseEntity<>(nuevaFranquicia, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Franquicia>> listarFranquicias() {
        List<Franquicia> franquicias = franquiciaService.listarFranquicias();
        return new ResponseEntity<>(franquicias, HttpStatus.OK);
    }
}

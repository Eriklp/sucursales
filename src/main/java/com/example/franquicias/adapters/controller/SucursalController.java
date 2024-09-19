package com.example.franquicias.adapters.controller;

import com.example.franquicias.application.service.SucursalService;
import com.example.franquicias.domain.model.Sucursal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/franquicias/{franquiciaId}/sucursales")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @PostMapping
    public Mono<ResponseEntity<Sucursal>> agregarSucursal(@PathVariable Long franquiciaId, @RequestBody Sucursal sucursal) {
        return sucursalService.agregarSucursal(franquiciaId, sucursal)
                .map(suc -> ResponseEntity.ok(suc));
    }

    @GetMapping
    public Flux<Sucursal> listarSucursales(@PathVariable Long franquiciaId) {
        return sucursalService.listarSucursalesPorFranquicia(franquiciaId);
    }
}

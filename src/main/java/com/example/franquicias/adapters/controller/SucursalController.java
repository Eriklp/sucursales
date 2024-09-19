package com.example.franquicias.adapters.controller;


import com.example.franquicias.application.service.SucursalService;
import com.example.franquicias.domain.model.Sucursal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/franquicias/{franquiciaId}/sucursales")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @PostMapping
    public ResponseEntity<Sucursal> agregarSucursal(@PathVariable Long franquiciaId, @RequestBody Sucursal sucursal) {
        return ResponseEntity.ok(sucursalService.agregarSucursal(franquiciaId, sucursal));
    }
}

package com.example.franquicias.controllers;

import com.example.franquicias.models.Sucursal;
import com.example.franquicias.models.Franquicia;
import com.example.franquicias.services.SucursalService;
import com.example.franquicias.services.FranquiciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/franchises")
public class FranquiciaController {

    @Autowired
    private FranquiciaService franchiseService;

    @Autowired
    private SucursalService sucursalService;

    @PostMapping
    public Franquicia createFranquicia(@RequestBody Franquicia franchise) {
        return franchiseService.saveFranquicia(franchise);
    }

    @PostMapping("/{franchiseId}/bucursales")
    public Sucursal addSucursalToFranquicia(@PathVariable Long franchiseId, @RequestBody Sucursal bucursal) {
        return sucursalService.saveSucursal(franchiseId, bucursal);
    }

    @DeleteMapping("/{franchiseId}/bucursales/{bucursalId}")
    public void deleteSucursal(@PathVariable Long franchiseId, @PathVariable Long bucursalId) {
        sucursalService.deleteSucursal(bucursalId);
    }
}
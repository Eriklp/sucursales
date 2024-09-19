package com.example.franquicias.adapters.controller;

import com.example.franquicias.application.service.ProductoService;
import com.example.franquicias.domain.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sucursales/{sucursalId}/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<Producto> agregarProducto(@PathVariable Long sucursalId, @RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.agregarProducto(sucursalId, producto));
    }
}


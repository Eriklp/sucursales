package com.example.franquicias.adapters.controller;

import com.example.franquicias.application.service.ProductoService;
import com.example.franquicias.domain.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/sucursales/{sucursalId}/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public Mono<ResponseEntity<Producto>> agregarProducto(@PathVariable Long sucursalId, @RequestBody Producto producto) {
        return productoService.agregarProducto(sucursalId, producto)
                .map(ResponseEntity::ok);
    }

    @PatchMapping("/{productoId}/modificar-stock")
    public Mono<ResponseEntity<Producto>> modificarStock(@PathVariable Long sucursalId, @PathVariable Long productoId, @RequestParam int nuevoStock) {
        return productoService.modificarStock(sucursalId, productoId, nuevoStock)
                .map(ResponseEntity::ok);
    }

    @GetMapping
    public Flux<Producto> listarProductosPorSucursal(@PathVariable Long sucursalId) {
        return productoService.listarProductosPorSucursal(sucursalId);
    }
}

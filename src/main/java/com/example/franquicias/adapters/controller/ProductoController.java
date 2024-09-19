package com.example.franquicias.adapters.controller;

import com.example.franquicias.application.service.ProductoService;
import com.example.franquicias.domain.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sucursales/{sucursalId}/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<Producto> agregarProducto(@PathVariable Long sucursalId, @RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.agregarProducto(sucursalId, producto));
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductosPorSucursal(@PathVariable Long sucursalId) {
        List<Producto> productos = productoService.listarProductosPorSucursal(sucursalId);
        return ResponseEntity.ok(productos);
    }

    @PutMapping("/{productoId}/stock")
    public ResponseEntity<Producto> actualizarStockProducto(
            @PathVariable Long sucursalId,
            @PathVariable Long productoId,
            @RequestParam int nuevoStock) {
        Producto productoActualizado = productoService.actualizarStockProducto(sucursalId, productoId, nuevoStock);
        return ResponseEntity.ok(productoActualizado);
    }
}

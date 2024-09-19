package com.example.franquicias.application.service;

import com.example.franquicias.domain.model.Producto;
import com.example.franquicias.domain.model.Sucursal;
import com.example.franquicias.domain.repository.ProductoRepository;
import com.example.franquicias.domain.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    public Mono<Producto> agregarProducto(Long sucursalId, Producto producto) {
        return Mono.fromCallable(() -> {
            // Busca la sucursal por ID y establece la relación en el producto
            Sucursal sucursal = sucursalRepository.findById(sucursalId)
                    .orElseThrow(() -> new IllegalArgumentException("Sucursal no encontrada."));
            producto.setSucursal(sucursal);
            return productoRepository.save(producto);
        });
    }

    public Mono<Producto> modificarStock(Long sucursalId, Long productoId, int nuevoStock) {
        return Mono.fromCallable(() -> {
            return productoRepository.findByIdAndSucursalId(productoId, sucursalId)
                    .map(producto -> {
                        producto.setStock(nuevoStock);
                        return productoRepository.save(producto);
                    })
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado."));
        });
    }

    public Flux<Producto> listarProductosPorSucursal(Long sucursalId) {
        return Flux.defer(() -> Flux.fromIterable(productoRepository.findAllBySucursalId(sucursalId)));
    }
}

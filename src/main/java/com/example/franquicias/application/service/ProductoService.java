package com.example.franquicias.application.service;

import com.example.franquicias.domain.model.Producto;
import com.example.franquicias.domain.model.Sucursal;
import com.example.franquicias.domain.repository.ProductoRepository;
import com.example.franquicias.domain.repository.SucursalRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final SucursalRepository sucursalRepository;

    public ProductoService(ProductoRepository productoRepository, SucursalRepository sucursalRepository) {
        this.productoRepository = productoRepository;
        this.sucursalRepository = sucursalRepository;
    }

    public Producto agregarProducto(Long sucursalId, Producto producto) {
        Sucursal sucursal = sucursalRepository.findById(sucursalId).orElseThrow();
        producto.setSucursal(sucursal);
        return productoRepository.save(producto);
    }
}

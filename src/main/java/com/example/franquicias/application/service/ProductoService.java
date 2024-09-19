package com.example.franquicias.application.service;

import com.example.franquicias.domain.model.Producto;
import com.example.franquicias.domain.model.Sucursal;
import com.example.franquicias.domain.repository.ProductoRepository;
import com.example.franquicias.domain.repository.SucursalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Producto actualizarStockProducto(Long sucursalId, Long productoId, int nuevoStock) {
        Producto producto = productoRepository.findByIdAndSucursalId(productoId, sucursalId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado en la sucursal"));

        producto.setStock(nuevoStock); // Actualizamos el stock
        return productoRepository.save(producto);
    }

    public List<Producto> listarProductosPorSucursal(Long sucursalId) {
        return productoRepository.findAllBySucursalId(sucursalId);
    }
}

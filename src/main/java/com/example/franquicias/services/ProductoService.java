package com.example.franquicias.services;

import com.example.franquicias.models.Sucursal;
import com.example.franquicias.models.Producto;
import com.example.franquicias.repository.SucursalRepository;
import com.example.franquicias.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    public Producto saveProducto(Long sucursalId, Producto product) {
        Sucursal sucursal = sucursalRepository.findById(sucursalId).orElseThrow();
        sucursal.getProductos().add(product);
        sucursalRepository.save(sucursal);
        return product;
    }

    public void deleteProducto(Long productId) {
        productRepository.deleteById(productId);
    }

    public Optional<Producto> findProductoWithHighestStock(Long franchiseId) {
        return sucursalRepository.findAll().stream()
                .flatMap(sucursal -> sucursal.getProductos().stream())
                .max((p1, p2) -> Integer.compare(p1.getStock(), p2.getStock()));
    }
}

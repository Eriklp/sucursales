package com.example.franquicias.domain.repository;

import com.example.franquicias.domain.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByIdAndSucursalId(Long id, Long sucursalId);
    List<Producto> findAllBySucursalId(Long sucursalId);
}

package com.example.franquicias.domain.repository;

import com.example.franquicias.domain.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
    List<Sucursal> findByFranquiciaId(Long franquiciaId);
}


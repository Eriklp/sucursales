package com.example.franquicias.domain.repository;

import com.example.franquicias.domain.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
}


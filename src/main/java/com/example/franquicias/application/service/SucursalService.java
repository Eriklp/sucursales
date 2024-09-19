package com.example.franquicias.application.service;

import com.example.franquicias.domain.model.Franquicia;
import com.example.franquicias.domain.model.Sucursal;
import com.example.franquicias.domain.repository.FranquiciaRepository;
import com.example.franquicias.domain.repository.SucursalRepository;
import org.springframework.stereotype.Service;

@Service
public class SucursalService {
    private final SucursalRepository sucursalRepository;
    private final FranquiciaRepository franquiciaRepository;

    public SucursalService(SucursalRepository sucursalRepository, FranquiciaRepository franquiciaRepository) {
        this.sucursalRepository = sucursalRepository;
        this.franquiciaRepository = franquiciaRepository;
    }

    public Sucursal agregarSucursal(Long franquiciaId, String nombre) {
        Franquicia franquicia = franquiciaRepository.findById(franquiciaId)
                .orElseThrow(() -> new RuntimeException("Franquicia no encontrada"));
        Sucursal sucursal = new Sucursal(nombre, franquicia);
        return sucursalRepository.save(sucursal);
    }
}

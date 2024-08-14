package com.example.franquicias.services;

import com.example.franquicias.models.Sucursal;
import com.example.franquicias.models.Franquicia;
import com.example.franquicias.repository.SucursalRepository;
import com.example.franquicias.repository.FranquiciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SucursalService {

    @Autowired
    private SucursalRepository SucursalRepository;

    @Autowired
    private FranquiciaRepository FranquiciaRepository;

    public Sucursal saveSucursal(Long FranquiciaId, Sucursal Sucursal) {
        Franquicia Franquicia = FranquiciaRepository.findById(FranquiciaId).orElseThrow();
        Franquicia.getSucursales().add(Sucursal);
        FranquiciaRepository.save(Franquicia);
        return Sucursal;
    }

    public void deleteSucursal(Long id) {
        SucursalRepository.deleteById(id);
    }
}

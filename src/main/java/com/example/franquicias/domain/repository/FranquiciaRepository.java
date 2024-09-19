package com.example.franquicias.domain.repository;

import com.example.franquicias.domain.model.Franquicia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranquiciaRepository extends JpaRepository<Franquicia, Long> {
}

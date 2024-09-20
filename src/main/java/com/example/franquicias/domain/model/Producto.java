package com.example.franquicias.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Integer stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sucursal_id", nullable = false)
    @JsonBackReference // Rompe la serialización cíclica con Sucursal
    private Sucursal sucursal;

    // Constructor
    public Producto(String nombre, Integer stock, Sucursal sucursal) {
        this.nombre = nombre;
        this.stock = stock;
        this.sucursal = sucursal;
    }
}

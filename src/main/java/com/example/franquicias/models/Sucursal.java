package com.example.franquicias.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Data
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Producto> products;

    public List<Producto> getProductos() {
        return this.products;
    }

    public void setProductos(List<Producto> productos) {
        this.products = productos;
    }
}

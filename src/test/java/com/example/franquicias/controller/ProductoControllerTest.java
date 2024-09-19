package com.example.franquicias.controller;

import com.example.franquicias.adapters.controller.ProductoController;
import com.example.franquicias.adapters.controller.SucursalController;
import com.example.franquicias.application.service.ProductoService;
import com.example.franquicias.domain.model.Producto;
import com.example.franquicias.domain.model.Sucursal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.reactive.server.WebTestClient.bindToController;

@ContextConfiguration(classes={ProductoController.class})
@WebFluxTest()
public class ProductoControllerTest {

    @MockBean
    private ProductoService productoService;

    @InjectMocks
    private ProductoController productoController;

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAgregarProducto() {
        Sucursal sucursal = new Sucursal();
        sucursal.setId(1L);
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto 1");
        producto.setStock(10);
        producto.setSucursal(sucursal);

        when(productoService.agregarProducto(any(Long.class), any(Producto.class)))
                .thenReturn(Mono.just(producto));

        webTestClient.post()
                .uri("/sucursales/1/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(producto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Producto.class);
    }

    @Test
    public void testEliminarProducto() {
        when(productoService.eliminarProducto(any(Long.class), any(Long.class)))
                .thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/sucursales/1/productos/1")
                .exchange()
                .expectStatus().isOk();
    }

    // Otros tests adicionales como modificar stock, listar productos...
}

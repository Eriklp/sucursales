package com.example.franquicias.controller;

import com.example.franquicias.adapters.controller.SucursalController;
import com.example.franquicias.application.service.FranquiciaService;
import com.example.franquicias.application.service.SucursalService;
import com.example.franquicias.domain.model.Sucursal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.reactive.server.WebTestClient.bindToController;

@ContextConfiguration(classes={SucursalController.class})
@WebFluxTest()
public class SucursalControllerTest {

    @MockBean
    private SucursalService sucursalService;

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        //webTestClient = bindToController(sucursalController).build();
    }

    @Test
    public void testAgregarSucursal() {
        Sucursal sucursal = new Sucursal();
        sucursal.setId(1L);
        sucursal.setNombre("Sucursal 1");

        when(sucursalService.agregarSucursal(any(Long.class), any(Sucursal.class)))
                .thenReturn(Mono.just(sucursal));

        webTestClient.post()
                .uri("/franquicias/1/sucursales")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(sucursal), Sucursal.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Sucursal.class);
    }

    @Test
    public void testListarSucursales() {
        Sucursal sucursal1 = new Sucursal();
        sucursal1.setId(1L);
        sucursal1.setNombre("Sucursal 1");

        Sucursal sucursal2 = new Sucursal();
        sucursal2.setId(2L);
        sucursal2.setNombre("Sucursal 2");

        when(sucursalService.listarSucursalesPorFranquicia(any(Long.class)))
                .thenReturn(Flux.just(sucursal1, sucursal2));

        webTestClient.get()
                .uri("/franquicias/1/sucursales")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Sucursal.class);
    }
}

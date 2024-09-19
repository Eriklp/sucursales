package com.example.franquicias.controller;

import com.example.franquicias.adapters.controller.FranquiciaController;
import com.example.franquicias.application.service.FranquiciaService;
import com.example.franquicias.domain.model.Franquicia;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ContextConfiguration(classes={FranquiciaController.class})
@WebFluxTest()
class FranquiciaControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private FranquiciaService franquiciaService;

    @Test
    void testAgregarFranquicia() {
        Franquicia franquicia = new Franquicia();
        Mockito.when(franquiciaService.agregarFranquicia(Mockito.any(Franquicia.class)))
                .thenReturn(Mono.just(franquicia));

        webTestClient.post()
                .uri("/franquicias")
                .bodyValue(franquicia)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(franquicia.getId());
    }

    @Test
    void testListarFranquicias() {
        Franquicia franquicia1 = new Franquicia( "Franquicia 1");
        Franquicia franquicia2 = new Franquicia("Franquicia 2");
        Mockito.when(franquiciaService.listarFranquicias()).thenReturn(Flux.just(franquicia1, franquicia2));

        webTestClient.get()
                .uri("/franquicias")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].nombre").isEqualTo("Franquicia 1")
                .jsonPath("$[1].nombre").isEqualTo("Franquicia 2");
    }
}

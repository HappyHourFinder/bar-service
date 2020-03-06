package com.mathieuaime.hhf.bar.controller;

import static org.mockito.Mockito.verify;

import com.mathieuaime.hhf.bar.api.Bar;
import com.mathieuaime.hhf.bar.model.BarDocument;
import com.mathieuaime.hhf.bar.repository.BarRepository;
import com.mathieuaime.hhf.bar.service.BarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@Import({BarService.class, ModelMapper.class})
@WebFluxTest(BarController.class)
class BarControllerTest {

  @Autowired
  private WebTestClient webTestClient;

  @MockBean
  private BarRepository barRepository;

  @Test
  void getBars() {
    BarDocument barDocument1 = new BarDocument("1", "Bar 1");
    BarDocument barDocument2 = new BarDocument("2", "Bar 2");

    Mockito.when(barRepository.findAll()).thenReturn(Flux.just(barDocument1, barDocument2));

    Bar bar1 = new Bar("1", "Bar 1");
    Bar bar2 = new Bar("2", "Bar 2");
    webTestClient.get()
        .uri("/")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(Bar.class).contains(bar1, bar2);

    verify(this.barRepository).findAll();
  }

  @Test
  void getBar() {
    BarDocument barDocument = new BarDocument("1", "Bar 1");

    Mockito.when(barRepository.findById("1")).thenReturn(Mono.just(barDocument));

    Bar bar = new Bar("1", "Bar 1");
    webTestClient.get()
        .uri("/{uuid}", 1)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectBody(Bar.class).isEqualTo(bar);

    verify(this.barRepository).findById("1");
  }

  @Test
  void saveBar() {
    BarDocument barDocument = new BarDocument(null, "New Bar");
    BarDocument barDocumentCreated = new BarDocument("newUuid", "New Bar");

    Mockito.when(barRepository.save(barDocument)).thenReturn(Mono.just(barDocumentCreated));

    Bar bar = new Bar(null, "New Bar");
    Bar barCreated = new Bar("newUuid", "New Bar");

    webTestClient.post()
        .uri("/")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .body(Mono.just(bar), Bar.class)
        .exchange()
        .expectStatus().isCreated()
        .expectBody(Bar.class).isEqualTo(barCreated);

    verify(this.barRepository).save(barDocument);
  }

  @Test
  void deleteBar() {
    webTestClient.delete()
        .uri("/{uuid}", 1)
        .exchange()
        .expectStatus().isOk();

    verify(this.barRepository).deleteById("1");
  }
}
package com.mathieuaime.hhf.bar.controller;

import com.mathieuaime.hhf.bar.api.Bar;
import com.mathieuaime.hhf.bar.service.BarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class BarController {

  private final BarService barService;

  @Autowired
  public BarController(BarService barService) {
    this.barService = barService;
  }

  @GetMapping
  public Flux<Bar> getBars() {
    return barService.getByUuid();
  }

  @GetMapping("/{uuid}")
  public Mono<Bar> getBar(@PathVariable("uuid") String uuid) {
    return barService.getByUuid(uuid);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Bar> saveBar(@RequestBody Bar bar) {
    return barService.save(bar);
  }

  @DeleteMapping("/{uuid}")
  public Mono<Void> deleteBarByUuid(@PathVariable("uuid") String uuid) {
    return barService.deleteByUuid(uuid);
  }
}

package com.mathieuaime.hhf.bar.service;

import com.mathieuaime.hhf.bar.api.Bar;
import com.mathieuaime.hhf.bar.model.BarDocument;
import com.mathieuaime.hhf.bar.repository.BarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BarService {

  private final BarRepository repository;
  private final ModelMapper mapper;

  @Autowired
  public BarService(BarRepository repository, ModelMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public Flux<Bar> getByUuid() {
    return repository.findAll().map(this::toApi);
  }

  public Mono<Bar> getByUuid(String uuid) {
    return repository.findById(uuid).map(this::toApi);
  }

  public Mono<Bar> save(Bar bar) {
    return repository.save(mapper.map(bar, BarDocument.class)).map(this::toApi);
  }

  public Mono<Void> deleteByUuid(String uuid) {
    return repository.deleteById(uuid);
  }

  private Bar toApi(BarDocument barDocument) {
    return mapper.map(barDocument, Bar.class);
  }
}

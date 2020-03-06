package com.mathieuaime.hhf.bar.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mathieuaime.hhf.bar.api.Bar;
import com.mathieuaime.hhf.bar.model.BarDocument;
import com.mathieuaime.hhf.bar.repository.BarRepository;
import com.mathieuaime.hhf.bar.service.BarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class BarServiceTest {

  @Mock
  private BarRepository barRepository;

  @Mock
  private ModelMapper modelMapper;

  @InjectMocks
  private BarService barService;

  @Test
  void getBars() {
    Bar bar = new Bar();
    BarDocument barDocument = new BarDocument();

    when(barRepository.findAll()).thenReturn(Flux.just(barDocument));
    when(modelMapper.map(barDocument, Bar.class)).thenReturn(bar);

    Flux<Bar> bars = barService.getByUuid();

    verify(this.barRepository).findAll();
    assertThat(bars.count().block()).isEqualTo(1L);
    assertThat(bars.blockFirst()).isEqualTo(bar);
  }

  @Test
  void saveBar() {
    Bar bar = new Bar();
    BarDocument barDocument = new BarDocument();

    when(barRepository.save(barDocument)).thenReturn(Mono.just(barDocument));

    when(modelMapper.map(bar, BarDocument.class)).thenReturn(barDocument);
    when(modelMapper.map(barDocument, Bar.class)).thenReturn(bar);

    Mono<Bar> bars = barService.save(bar);

    verify(this.barRepository).save(barDocument);
    assertThat(bars.block()).isEqualTo(bar);
  }

  @Test
  void deleteBar() {
    barService.deleteByUuid("uuid");

    verify(this.barRepository).deleteById("uuid");
  }
}

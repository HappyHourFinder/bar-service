package com.mathieuaime.hhf.core.bar.service.impl

import com.mathieuaime.hhf.core.bar.api.Bar
import com.mathieuaime.hhf.core.bar.model.BarEntity
import com.mathieuaime.hhf.core.bar.repository.BarRepository
import com.mathieuaime.hhf.core.bar.service.BarServiceImpl
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.modelmapper.ModelMapper
import java.util.Optional

@ExtendWith(MockitoExtension::class)
internal class BarServiceTest {
    @Mock
    private lateinit var barRepository: BarRepository

    @Mock
    private lateinit var modelMapper: ModelMapper

    @InjectMocks
    private lateinit var barService: BarServiceImpl

    @Test
    fun getBars() {
        val barExpected = Bar("1", "name", 0.0, 0.0)
        val barEntity = BarEntity(uuid = "1", name = "name", latitude = 0.0, longitude = 0.0)

        Mockito.`when`(barRepository.findAll()).thenReturn(listOf(barEntity))
        Mockito.`when`(modelMapper.map(barEntity, Bar::class.java)).thenReturn(barExpected)

        val bars = barService.getBars()

        Mockito.verify(barRepository).findAll()
        Assertions.assertThat(bars.size).isEqualTo(1L)
        Assertions.assertThat(bars[0]).isEqualTo(barExpected)
    }

    @Test
    fun getBarByUuid() {
        val barExpected = Bar("1", "name", 0.0, 0.0)
        val barEntity = BarEntity(uuid = "1", name = "name", latitude = 0.0, longitude = 0.0)

        Mockito.`when`(barRepository.findById("uuid")).thenReturn(Optional.of(barEntity))
        Mockito.`when`(modelMapper.map(barEntity, Bar::class.java)).thenReturn(barExpected)

        val bar = barService.getByUuid("uuid")

        Mockito.verify(barRepository).findById("uuid")
        Assertions.assertThat(bar).contains(barExpected)
    }

    @Test
    fun getBarByUuidNotFound() {
        Mockito.`when`(barRepository.findById("uuid")).thenReturn(Optional.empty())

        val bar = barService.getByUuid("uuid")

        Mockito.verify(barRepository).findById("uuid")
        Assertions.assertThat(bar).isEmpty
    }

    @Test
    fun saveBar() {
        val barExpected = Bar("1", "name", 0.0, 0.0)
        val barEntity = BarEntity(uuid = "1", name = "name", latitude = 0.0, longitude = 0.0)

        Mockito.`when`(barRepository.save(barEntity)).thenReturn(barEntity)
        Mockito.`when`(modelMapper.map(barExpected, BarEntity::class.java)).thenReturn(barEntity)
        Mockito.`when`(modelMapper.map(barEntity, Bar::class.java)).thenReturn(barExpected)

        val bar = barService.save(barExpected)

        Mockito.verify(barRepository).save(barEntity)
        Assertions.assertThat(bar).isEqualTo(barExpected)
    }

    @Test
    fun deleteBar() {
        barService.deleteByUuid("uuid")
        Mockito.verify(barRepository).deleteById("uuid")
    }
}
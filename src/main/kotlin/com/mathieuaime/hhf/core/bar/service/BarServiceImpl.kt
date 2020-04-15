package com.mathieuaime.hhf.core.bar.service

import com.mathieuaime.hhf.core.bar.api.Bar
import com.mathieuaime.hhf.core.bar.model.BarEntity
import com.mathieuaime.hhf.core.bar.repository.BarRepository
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import java.util.Optional

@Service
internal class BarServiceImpl constructor(private val repository: BarRepository, private val mapper: ModelMapper) : BarService {
    override fun getBars(): List<Bar> = repository.findAll().map(this::toApi)

    override fun getByUuid(uuid: String): Optional<Bar> = repository.findById(uuid).map(this::toApi)

    override fun save(bar: Bar): Bar = toApi(repository.save(toEntity(bar)))

    override fun deleteByUuid(uuid: String) = repository.deleteById(uuid)

    private fun toEntity(bar: Bar): BarEntity = mapper.map(bar, BarEntity::class.java)
    private fun toApi(barEntity: BarEntity): Bar = mapper.map(barEntity, Bar::class.java)
}
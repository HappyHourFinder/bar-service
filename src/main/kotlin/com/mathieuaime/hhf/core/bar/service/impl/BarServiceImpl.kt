package com.mathieuaime.hhf.core.bar.service.impl

import com.mathieuaime.hhf.core.bar.api.Bar
import com.mathieuaime.hhf.core.bar.model.BarEntity
import com.mathieuaime.hhf.core.bar.repository.BarRepository
import com.mathieuaime.hhf.core.bar.service.BarService
import org.springframework.stereotype.Service
import java.util.*
import java.util.UUID.randomUUID

@Service
internal class BarServiceImpl(private val repository: BarRepository) : BarService {
    override fun getBars(): List<Bar> = repository.findAll().map(BarEntity::toApi)

    override fun getByUuid(uuid: String): Optional<Bar> = repository.findById(uuid).map(BarEntity::toApi)

    override fun save(bar: Bar): Bar = repository.save(bar.toEntity()).toApi()

    override fun deleteByUuid(uuid: String) = repository.deleteById(uuid)
}

private fun BarEntity.toApi() = Bar(uuid, name, latitude, longitude)

private fun Bar.toEntity() = BarEntity(uuid ?: randomUUID().toString(), name, latitude, longitude)
package com.mathieuaime.hhf.core.bar.service

import com.mathieuaime.hhf.core.bar.api.Bar
import java.util.*

interface BarService {
    fun getBars(): List<Bar>

    fun getByUuid(uuid: String): Optional<Bar>

    fun save(bar: Bar): Bar

    fun deleteByUuid(uuid: String)
}
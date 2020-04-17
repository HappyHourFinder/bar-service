package com.mathieuaime.hhf.core.bar.api

import com.mathieuaime.hhf.core.bar.model.BarEntity
import java.beans.ConstructorProperties
import java.util.*

data class Bar @ConstructorProperties("uuid", "name", "latitude", "longitude")
constructor(var uuid: String?, var name: String, var latitude: Double, var longitude: Double)

fun Bar.toEntity() = BarEntity(uuid ?: UUID.randomUUID().toString(), name, latitude, longitude)
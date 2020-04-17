package com.mathieuaime.hhf.core.bar.model

import com.mathieuaime.hhf.core.bar.api.Bar
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class BarEntity(
        @Id
        @GeneratedValue
        val uuid: String,
        @Column(nullable = false)
        val name: String,
        @Column(nullable = false)
        val latitude: Double,
        @Column(nullable = false)
        val longitude: Double
)

fun BarEntity.toApi() = Bar(uuid, name, latitude, longitude)
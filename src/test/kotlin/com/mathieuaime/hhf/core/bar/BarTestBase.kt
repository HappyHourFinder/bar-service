package com.mathieuaime.hhf.core.bar

import com.mathieuaime.hhf.core.bar.api.Bar
import com.mathieuaime.hhf.core.bar.controller.BarController
import com.mathieuaime.hhf.core.bar.service.BarService
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.junit.jupiter.api.BeforeEach
import java.util.*

abstract class BarTestBase {
    private val barController = BarController(barService())

    @BeforeEach
    fun setup() {
        RestAssuredMockMvc.standaloneSetup(barController)
    }

    private fun barService(): BarService {
        val fakeBar = Bar("1", "Bar", 1.0, 2.0)
        val fakeBar2 = Bar("2", "Other bar", 2.0, 3.0)
        val bars = listOf(fakeBar, fakeBar2)

        return object : BarService {
            override fun getBars(): List<Bar> {
                return bars
            }

            override fun getByUuid(uuid: String): Optional<Bar> {
                return Optional.ofNullable(bars.find { it.uuid == uuid })
            }

            override fun save(bar: Bar): Bar {
                return Bar(UUID.randomUUID().toString(), bar.name, bar.latitude, bar.longitude)
            }

            override fun deleteByUuid(uuid: String) {
            }
        }
    }
}
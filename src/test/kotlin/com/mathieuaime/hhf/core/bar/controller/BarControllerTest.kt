package com.mathieuaime.hhf.core.bar.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.mathieuaime.hhf.core.bar.api.Bar
import com.mathieuaime.hhf.core.bar.service.BarService
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*

@ExtendWith(SpringExtension::class)
@WebMvcTest(BarController::class)
internal class BarControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var barService: BarService

    @Test
    fun getBars() {
        val bar1 = Bar("1", "Bar 1", 1.0, 2.0)
        val bar2 = Bar("2", "Bar 2", 2.0, 3.0)
        Mockito.`when`(barService.getBars()).thenReturn(listOf(bar1, bar2))

        mockMvc.perform(MockMvcRequestBuilders.get("/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize<Any>(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].uuid", Matchers.`is`("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.`is`("Bar 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].latitude").value(Matchers.`is`(1.0), Double::class.java))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].longitude").value(Matchers.`is`(2.0), Double::class.java))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].uuid", Matchers.`is`("2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.`is`("Bar 2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].latitude").value(Matchers.`is`(2.0), Double::class.java))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].longitude").value(Matchers.`is`(3.0), Double::class.java))

        Mockito.verify(barService).getBars()
    }

    @Test
    fun getBar() {
        val bar = Bar("1", "Bar 1", 1.0, 2.0)
        Mockito.`when`(barService.getByUuid("1")).thenReturn(Optional.of(bar))

        mockMvc.perform(MockMvcRequestBuilders.get("/{uuid}", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.uuid", Matchers.`is`("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.`is`("Bar 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.latitude").value(Matchers.`is`(1.0), Double::class.java))
                .andExpect(MockMvcResultMatchers.jsonPath("$.longitude").value(Matchers.`is`(2.0), Double::class.java))

        Mockito.verify(barService).getByUuid("1")
    }

    @Test
    fun getBarNotFound() {
        Mockito.`when`(barService.getByUuid("1")).thenReturn(Optional.empty())

        mockMvc.perform(MockMvcRequestBuilders.get("/{uuid}", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound)

        Mockito.verify(barService).getByUuid("1")
    }

    @Test
    fun saveBar() {
        val bar = Bar("1", "Bar 1", 1.0, 2.0)
        Mockito.`when`(barService.save(bar)).thenReturn(bar)

        mockMvc.perform(MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bar)))
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andExpect(MockMvcResultMatchers.jsonPath("$.uuid", Matchers.`is`("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.`is`("Bar 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.latitude").value(Matchers.`is`(1.0), Double::class.java))
                .andExpect(MockMvcResultMatchers.jsonPath("$.longitude").value(Matchers.`is`(2.0), Double::class.java))

        Mockito.verify(barService).save(bar)
    }

    @Test
    fun deleteBar() {
        mockMvc.perform(MockMvcRequestBuilders.delete("/{uuid}", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
        Mockito.verify(barService).deleteByUuid("1")
    }
}
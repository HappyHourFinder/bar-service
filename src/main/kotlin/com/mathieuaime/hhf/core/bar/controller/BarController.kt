package com.mathieuaime.hhf.core.bar.controller

import com.mathieuaime.hhf.core.bar.api.Bar
import com.mathieuaime.hhf.core.bar.service.BarService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class BarController(private val barService: BarService) {
    @GetMapping
    fun getBars(): List<Bar> = barService.getBars()

    @GetMapping("/{uuid}")
    fun getBar(@PathVariable("uuid") uuid: String): ResponseEntity<Bar> = ResponseEntity.of(barService.getByUuid(uuid))

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun saveBar(@RequestBody bar: Bar): Bar = barService.save(bar)

    @DeleteMapping("/{uuid}")
    fun deleteBarByUuid(@PathVariable("uuid") uuid: String) = barService.deleteByUuid(uuid)
}
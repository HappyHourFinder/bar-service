package com.mathieuaime.hhf.core.bar.controller

import com.mathieuaime.hhf.core.bar.api.Bar
import com.mathieuaime.hhf.core.bar.service.BarService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/bars")
@RestController
class BarController(private val barService: BarService) {
    @GetMapping
    fun getBars(): List<Bar> = barService.getBars()

    @GetMapping("/{uuid}")
    fun getBar(@PathVariable("uuid") uuid: String): ResponseEntity<Bar> = ResponseEntity.of(barService.getByUuid(uuid))

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveBar(@RequestBody bar: Bar): Bar = barService.save(bar)

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBarByUuid(@PathVariable("uuid") uuid: String) = barService.deleteByUuid(uuid)
}
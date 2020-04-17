package com.mathieuaime.hhf.core.bar

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class BarServiceApplication

fun main(args: Array<String>) {
    runApplication<BarServiceApplication>(*args)
}
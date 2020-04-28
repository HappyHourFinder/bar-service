package com.mathieuaime.hhf.core.bar

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
open class BarServiceApplication

fun main(args: Array<String>) {
    runApplication<BarServiceApplication>(*args)
}
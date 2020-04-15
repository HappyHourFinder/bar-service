package com.mathieuaime.hhf.core.bar.config

import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MapperConfiguration {
    @Bean
    fun modelMapper(): ModelMapper = ModelMapper()
}
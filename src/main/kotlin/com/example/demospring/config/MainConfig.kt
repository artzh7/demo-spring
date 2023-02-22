package com.example.demospring.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class MainConfig {

  @Bean
  fun restTemplate() = RestTemplate()
}
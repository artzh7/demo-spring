package com.example.demospring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class DemoSpringApplication

fun main(args: Array<String>) {
  runApplication<DemoSpringApplication>(*args)
}

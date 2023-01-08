package com.example.demospring.animal

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/animal")
class AnimalController(
  private val animalService: AnimalService,
) {
  @GetMapping
  fun findAll() = animalService.findAll()

  @GetMapping("/{id}")
  fun findById(@PathVariable id: Long) = animalService.findById(id)
}
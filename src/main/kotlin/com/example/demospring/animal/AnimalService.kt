package com.example.demospring.animal

import org.springframework.stereotype.Service

@Service
class AnimalService(
  private val animalRepository: AnimalRepository,
) {

  fun findAll() = animalRepository.findAll()

  fun findById(id: Long) = animalRepository.findById(id)
}
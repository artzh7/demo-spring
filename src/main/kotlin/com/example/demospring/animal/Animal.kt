package com.example.demospring.animal

internal const val ANIMAL_TABLE = "animal"

data class Animal(
  val id: Long,
  val title: String,
  val description: String? = null,
)

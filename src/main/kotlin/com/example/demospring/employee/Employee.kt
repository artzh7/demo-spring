package com.example.demospring.employee

internal const val EMPLOYEE_TABLE = "employee"

data class Employee(
  val id: Long,
  val firstName: String,
  val lastName: String,
  val age: Int,
)

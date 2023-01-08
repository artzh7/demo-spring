package com.example.demospring.employee

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/employee")
class EmployeeController(
  private val employeeService: EmployeeService,
) {
  @GetMapping
  fun findAll() = employeeService.findAll()

  @GetMapping("/{id}")
  fun findById(@PathVariable id: Long) = employeeService.findById(id)
}
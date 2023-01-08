package com.example.demospring.employee

import org.springframework.stereotype.Service

@Service
class EmployeeService(
  private val employeeRepository: EmployeeRepository,
) {

  fun findAll() = employeeRepository.findAll()

  fun findById(id: Long) = employeeRepository.findById(id)
}
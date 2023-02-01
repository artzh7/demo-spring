package com.example.demospring.userrequest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserRequestController(
  private val userRequestService: UserRequestService,
) {

  @GetMapping
  fun findAll() =
    userRequestService.findAll().toList().sortBy { it.id }

  @PostMapping
  fun add(userRequestInput: UserRequestInput) =
    userRequestService.add(userRequestInput)
}
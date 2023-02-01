package com.example.demospring.userrequest

import org.springframework.stereotype.Service
import java.util.stream.Stream

@Service
class UserRequestService(
  private val userRequestRepository: UserRequestRepository,
) {

  fun findAll(): Stream<UserRequestResponse> =
    userRequestRepository.findAll().map { it.toUserRequestResponse() }

  fun add(userRequestInput: UserRequestInput): UserRequest =
    userRequestRepository.add(userRequestInput.toUserRequest())
}
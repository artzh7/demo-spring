package com.example.demospring.userrequest

import java.time.*
import java.time.format.DateTimeFormatter

internal const val USER_REQUEST_TABLE = "user_request"

data class UserRequest(
  val id: Long,
  val username: String,
  val request: String,
  val createdAt: LocalDateTime,
) {
  fun toUserRequestResponse() = UserRequestResponse(
    username = this.username,
    request = this.request,
    createdAt = this.createdAt
      .atOffset(ZoneOffset.UTC)
      .atZoneSameInstant(ZoneId.systemDefault())
      .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
  )
}

data class UserRequestResponse(
  val username: String,
  val request: String,
  val createdAt: String,
)

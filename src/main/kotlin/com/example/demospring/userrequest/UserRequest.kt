package com.example.demospring.userrequest

import java.time.*
import java.time.format.DateTimeFormatter

internal const val USER_REQUEST_TABLE = "user_request"

data class UserRequest(
  var id: Long? = null,
  val username: String,
  val request: String,
  val createdAt: LocalDateTime,
) {
  fun toUserRequestResponse() = UserRequestResponse(
    id = this.id,
    username = this.username,
    request = this.request,
    createdAt = this.createdAt
      .atOffset(ZoneOffset.UTC)
      .atZoneSameInstant(ZoneId.systemDefault())
      .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))
  )
}

data class UserRequestInput(
  val username: String,
  val request: String,
) {
  fun toUserRequest() = UserRequest(
    username = this.username,
    request = this.request,
    createdAt = ZonedDateTime.now()
      .withZoneSameInstant(ZoneOffset.UTC)
      .toLocalDateTime()
  )
}

data class UserRequestResponse(
  val id: Long? = null,
  val username: String,
  val request: String,
  val createdAt: String,
)

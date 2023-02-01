package com.example.demospring.userrequest

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.util.stream.Stream

@Repository
class UserRequestRepository(
  private val primaryJdbcTemplate: NamedParameterJdbcTemplate,
) {

  fun findAll(): Stream<UserRequest> =
    primaryJdbcTemplate.queryForStream(
      "select * from $USER_REQUEST_TABLE",
      MapSqlParameterSource()
    ) {rs, _ -> rs.toUserRequest()}

  private fun ResultSet.toUserRequest() = UserRequest(
    id = getLong("id"),
    username = getString("username"),
    request = getString("request"),
    createdAt = getTimestamp("created_at").toLocalDateTime()
  )
}
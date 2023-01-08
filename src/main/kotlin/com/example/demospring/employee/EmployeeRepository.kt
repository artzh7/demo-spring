package com.example.demospring.employee

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.util.stream.Stream

@Repository
class EmployeeRepository(
  private val secondaryJdbcTemplate: NamedParameterJdbcTemplate,
) {
  fun findAll(): Stream<Employee> =
    secondaryJdbcTemplate.queryForStream(
      "select * from $EMPLOYEE_TABLE",
      MapSqlParameterSource()
    ) {rs, _ -> rs.toEmployee()}

  fun findById(id: Long): Employee? =
    secondaryJdbcTemplate.queryForObject(
      "select * from $EMPLOYEE_TABLE where id = :id",
      MapSqlParameterSource().addValue("id", id)
    ) {rs, _ -> rs.toEmployee()}

  private fun ResultSet.toEmployee() = Employee(
    id = getLong("id"),
    firstName = getString("first_name"),
    lastName = getString("last_name"),
    age = getInt("age")
  )
}
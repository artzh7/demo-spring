package com.example.demospring.animal

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.util.stream.Stream

@Repository
class AnimalRepository(
  private val primaryJdbcTemplate: NamedParameterJdbcTemplate,
) {
  fun findAll(): Stream<Animal> =
    primaryJdbcTemplate.queryForStream(
      "select * from $ANIMAL_TABLE",
      MapSqlParameterSource()
    ) {rs, _ -> rs.toAnimal()}

  fun findById(id: Long): Animal? =
    primaryJdbcTemplate.queryForObject(
      "select * from $ANIMAL_TABLE where id = :id",
      MapSqlParameterSource().addValue("id", id)
    ) {rs, _ -> rs.toAnimal()}

  private fun ResultSet.toAnimal() = Animal(
    id = getLong("id"),
    title = getString("title"),
    description = getString("description"),
  )
}
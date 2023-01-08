package com.example.demospring

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.PostgreSQLContainer

class TestcontainersInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {

  override fun initialize(context: ConfigurableApplicationContext) {
    container.start()
    secondaryContainer.start()
    TestPropertyValues.of(
      "spring.datasource.url=${container.jdbcUrl}",
      "spring.datasource.username=${container.username}",
      "spring.datasource.password=${container.password}",

      "spring.secondDatasource.url=${secondaryContainer.jdbcUrl}",
      "spring.secondDatasource.username=${secondaryContainer.username}",
      "spring.secondDatasource.password=${secondaryContainer.password}",
    ).applyTo(context.environment)
  }

  companion object {
    val container = PostgreSQLContainer<Nothing>("postgres:latest").apply {
      withDatabaseName("test")
      withUsername("test")
      withPassword("test")
      withInitScript("db/init-primary.sql")
    }

    val secondaryContainer = PostgreSQLContainer<Nothing>("postgres:latest").apply {
      withDatabaseName("test2")
      withUsername("test")
      withPassword("test")
      withInitScript("db/init-secondary.sql")
    }
  }
}
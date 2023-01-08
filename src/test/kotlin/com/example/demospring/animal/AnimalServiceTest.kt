package com.example.demospring.animal

import com.example.demospring.TestcontainersInitializer
import com.example.demospring.TestcontainersInitializer.Companion.container
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.DataAccessException
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.testcontainers.ext.ScriptUtils
import org.testcontainers.jdbc.JdbcDatabaseDelegate

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@ContextConfiguration(initializers = [TestcontainersInitializer::class])
class AnimalServiceTest {
  @Autowired
  private lateinit var animalService: AnimalService

  @BeforeEach
  fun refill() {
    ScriptUtils.runInitScript(JdbcDatabaseDelegate(container, ""), "db/animal/before-each.sql")
  }

  @Test
  fun `findAll finds two rows`() {
    val animals = animalService.findAll().toList()
    assertEquals(2, animals.size)
  }

  @Test
  fun `findById throws exception if not finds`() {
    var exception: Exception? = null
    try {
      animalService.findById(666)
    } catch (e: Exception) {
      exception = e
    }
    assertNotNull(exception)
    assertTrue(exception is DataAccessException)
  }
}
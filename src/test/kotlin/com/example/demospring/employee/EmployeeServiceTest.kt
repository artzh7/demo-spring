package com.example.demospring.employee

import com.example.demospring.TestcontainersInitializer
import com.example.demospring.TestcontainersInitializer.Companion.secondaryContainer
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
//import java.util.concurrent.CountDownLatch
//import java.util.concurrent.TimeUnit

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@ContextConfiguration(initializers = [TestcontainersInitializer::class])
class EmployeeServiceTest {
  @Autowired
  private lateinit var employeeService: EmployeeService

  @BeforeEach
  fun refill() {
    ScriptUtils.runInitScript(
      JdbcDatabaseDelegate(secondaryContainer, ""),
      "db/employee/before-each.sql"
    )
  }

  @Test
  fun `findAll finds three rows`() {
    val employees = employeeService.findAll().toList()
    assertEquals(3, employees.size)
  }

  @Test
  fun `findById throws exception if not finds`() {
    var exception: Exception? = null
    try {
      employeeService.findById(666)
    } catch (e: Exception) {
      exception = e
    }
    assertNotNull(exception)
    assertTrue(exception is DataAccessException)
  }

//  fun waitSeconds(seconds: Long) {
//    CountDownLatch(1).await(seconds, TimeUnit.SECONDS)
//  }
}
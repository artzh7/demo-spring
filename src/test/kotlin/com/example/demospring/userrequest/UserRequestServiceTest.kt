package com.example.demospring.userrequest

import com.example.demospring.TestcontainersInitializer
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.testcontainers.ext.ScriptUtils
import org.testcontainers.jdbc.JdbcDatabaseDelegate

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@ContextConfiguration(initializers = [TestcontainersInitializer::class])
class UserRequestServiceTest {
  @Autowired
  private lateinit var userRequestService: UserRequestService

  private val log = LoggerFactory.getLogger(this.javaClass)

  @BeforeEach
  fun refill() {
    ScriptUtils.runInitScript(
      JdbcDatabaseDelegate(TestcontainersInitializer.container, ""),
      "db/userrequest/before-each.sql"
    )
  }

  @Test
  fun test1() {
    val requests = userRequestService.findAll().toList()
    Assertions.assertEquals(2, requests.size)
    requests.forEach { log.info("$it") }
  }
}
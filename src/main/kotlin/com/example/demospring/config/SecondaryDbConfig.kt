package com.example.demospring.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.datasource.SimpleDriverDataSource
import org.springframework.util.ClassUtils
import java.sql.Driver

@Configuration
@ConfigurationProperties("spring.second-datasource")
class SecondaryDbConfig{
  lateinit var url: String
  lateinit var username: String
  lateinit var password: String
  lateinit var driverClassName: String

  @Bean
  fun secondaryJdbcTemplate(): NamedParameterJdbcTemplate {
    val driverClass = ClassUtils.resolveClassName(driverClassName, javaClass.classLoader)
    val driver = ClassUtils.getConstructorIfAvailable(driverClass)!!.newInstance() as Driver
    val datasource = SimpleDriverDataSource(driver, url, username, password)
    return NamedParameterJdbcTemplate(datasource)
  }
}
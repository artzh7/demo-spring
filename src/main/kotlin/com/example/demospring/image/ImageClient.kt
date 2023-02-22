package com.example.demospring.image

import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class ImageClient(
  private val restTemplate: RestTemplate
) {

  fun getImage(url: String): ByteArray? = try {
    restTemplate.getForObject(url, ByteArray::class.java)
  } catch (e: Exception) {
    null
  }
}
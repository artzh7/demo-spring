package com.example.demospring.image

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/image")
class ImageController(
  private val imageService: ImageService,
) {
  private val log = LoggerFactory.getLogger(this::class.java)

  @GetMapping("/preview")
  fun getImagePreview(@RequestParam url: String, @RequestParam size: Int): ImagePreview {
    val start = System.currentTimeMillis()
    val result = imageService.getImagePreview(url, size)
    val end = System.currentTimeMillis()
    log.info("getImagePreview: time = ${end - start} ms")
    return result
  }
}
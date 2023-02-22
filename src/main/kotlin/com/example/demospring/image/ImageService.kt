package com.example.demospring.image

import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.*
import javax.imageio.ImageIO

@Service
class ImageService(
  private val imageClient: ImageClient,
) {
  private val log = LoggerFactory.getLogger(this::class.java)

  @Cacheable(cacheNames = ["imagePreviews"], key = "#url.concat('-').concat(#size)")
  fun getImagePreview(url: String, size: Int): ImagePreview {
    var bais: ByteArrayInputStream? = null
    var baos: ByteArrayOutputStream? = null
    return try {
      val image1 = imageClient.getImage(url)
      image1?.let {
        bais = ByteArrayInputStream(image1)
        val originalImage = ImageIO.read(bais)
        val croppedImage = originalImage.getSubimage(0, 0, size, size)
        baos = ByteArrayOutputStream()
        ImageIO.write(croppedImage, "jpg", baos)
        val image2 = baos!!.toByteArray()

        val base64 = Base64.getEncoder().encodeToString(image2)
        ImagePreview(originalImage.height, originalImage.width, base64)
      } ?: ImagePreview()
    } catch (e: Exception) {
      log.error("getImagePreview: ${e.message}")
      ImagePreview()
    } finally {
      bais?.close()
      baos?.close()
    }
  }
}
package com.xyinc.poiservice

import com.xyinc.poiservice.model.PointOfInterest
import org.assertj.core.internal.bytebuddy.utility.RandomString
import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.postForEntity

/**
 * Util to help populate the database through HTTP POST requests.
 *
 */
class PopulateDatabase {

    val url: String = "http://localhost:8080/api/poi"

    val restTemplate = RestTemplate();

    @Test
    fun name() {
        for (i in 1..10_000) {
            val pointOfInterest = PointOfInterest(name = randomName(),
                                                  xCoordinate = randomInt(),
                                                  yCoordinate = randomInt())

            val post = restTemplate.postForEntity<PointOfInterest>(url, pointOfInterest, ResponseEntity::class)

            println(post)
        }
    }

    fun randomInt(): Int = (0..100).random()

    fun randomName(): String = RandomString().nextString()
}
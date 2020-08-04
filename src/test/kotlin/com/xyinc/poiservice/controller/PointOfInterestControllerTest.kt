package com.xyinc.poiservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.xyinc.poiservice.model.PointOfInterest
import com.xyinc.poiservice.service.PointOfInterestService
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import org.mockito.Answers
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

/**
 * Unit testing for PointOfInterestControllerImpl
 *
 * @author Danilo Guimaraes
 */
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/datasets/create-schema.sql", "/datasets/insert-pois.sql")
@WebMvcTest(PointOfInterestController::class)
class PointOfInterestControllerTest {

    private val objectMapper = ObjectMapper();

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean(answer = Answers.CALLS_REAL_METHODS)
    private lateinit var poiServiceMock : PointOfInterestService

    @Test
    fun `when call get all, must return 200`() {
        mockMvc.perform(get("/api/poi")
                .contentType(""))
                .andExpect(status().isOk)
                .andExpect(content().contentType("application/json"))
                .andReturn();
    }

    @Test
    fun `when call post, must return 200`() {
        val poi = PointOfInterest(name = "Whatever", xCoordinate = 20, yCoordinate = 25);

        given(poiServiceMock.add(poi)).willReturn(poi);

        mockMvc.perform(post("/api/poi")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(poi)))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.name", `is`("Whatever")))
                .andExpect(jsonPath("$.xcoordinate", `is`(20)))
                .andExpect(jsonPath("$.ycoordinate", `is`(25)));
    }
}


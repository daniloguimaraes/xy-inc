package com.xyinc.poiservice.service.impl

import com.xyinc.poiservice.exception.InvalidPointOfInterestException
import com.xyinc.poiservice.model.PointOfInterest
import com.xyinc.poiservice.service.PointOfInterestService
import org.amshove.kluent.shouldNotThrowTheException
import org.amshove.kluent.shouldThrowTheException
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

/**
 * Unit testing for PoiServiceImpl
 *
 * @author Danilo Guimaraes
 */
@SpringBootTest
class PointOfInterestServiceImplTest {

    private val validPoiName : String = "An Awesome Place";

    @Autowired
    lateinit var sut: PointOfInterestService;

    @Test
    fun `Given a valid PointOfInterest, when validate it, then must not throw Exception`() {
        // GIVEN
        val poi = createPointOfInterest(1L, validPoiName, 10, 10);

        // WHEN
        val validateResult = { sut.validate(poi) };

        // THEN
        validateResult shouldNotThrowTheException InvalidPointOfInterestException::class
    }

    @Test
    fun `Given a PointOfInterest without ID, when validate it, then must not throw Exception`() {
        // GIVEN
        val poi = createPointOfInterest(name = validPoiName, xCoordinate = 10, yCoordinate =  10);

        // WHEN
        val validateResult = { sut.validate(poi) };

        // THEN
        validateResult shouldNotThrowTheException InvalidPointOfInterestException::class
    }

    @Test
    fun `Given a PointOfInterest with XCoordinate equals 0, when validate it, then must not throw Exception`() {
        // GIVEN
        val poi = createPointOfInterest(1L, validPoiName, 0, 10);

        // WHEN
        val validateResult = { sut.validate(poi) };

        // THEN
        validateResult shouldNotThrowTheException InvalidPointOfInterestException::class
    }

    @Test
    fun `Given a PointOfInterest with negative XCoordinate, when validate it, then must throw Exception`() {
        // GIVEN
        val poi = createPointOfInterest(1L, validPoiName, -10, 10);

        // WHEN
        val validateResult = { sut.validate(poi) };

        // THEN
        validateResult shouldThrowTheException InvalidPointOfInterestException::class
    }

    @Test
    fun `Given a PointOfInterest with negative YCoordinate, when validate it, then must throw Exception`() {
        // GIVEN
        val poi = createPointOfInterest(1L, validPoiName, 10, -10);

        // WHEN
        val validateResult = { sut.validate(poi) };

        // THEN
        validateResult shouldThrowTheException InvalidPointOfInterestException::class
    }

    @Test
    fun `Given a PointOfInterest with both X and Y Coordinates as Negative, when validate it, then must throw Exception`() {
        // GIVEN
        val poi = createPointOfInterest(1L, validPoiName, -10, -10);

        // WHEN
        val validateResult = { sut.validate(poi) };

        // THEN
        validateResult shouldThrowTheException InvalidPointOfInterestException::class
    }

    @Test
    fun `Given an unnamed PointOfInterest, when validate it, then must throw Exception`() {
        // GIVEN
        val poi = createPointOfInterest(id = 1L, xCoordinate = 10, yCoordinate = 10);

        // WHEN
        val validateResult = { sut.validate(poi) };

        // THEN
        validateResult shouldThrowTheException InvalidPointOfInterestException::class
    }

    @Test
    fun `Given a PointOfInterest without XCoordinate, when validate it, then must throw Exception`() {
        // GIVEN
        val poi = createPointOfInterest(id = 1L, name = validPoiName, yCoordinate = 10);

        // WHEN
        val validateResult = { sut.validate(poi) };

        // THEN
        validateResult shouldThrowTheException InvalidPointOfInterestException::class
    }

    @Test
    fun `Given a PointOfInterest without YCoordinate, when validate it, then must throw Exception`() {
        // GIVEN
        val poi = createPointOfInterest(id = 1L, name = validPoiName, xCoordinate = 10);

        // WHEN
        val validateResult = { sut.validate(poi) };

        // THEN
        validateResult shouldThrowTheException InvalidPointOfInterestException::class
    }

    private fun createPointOfInterest(
            id: Long? = null,
            name: String = "",
            xCoordinate: Int = -1,
            yCoordinate: Int = -1): PointOfInterest {
        return PointOfInterest(id, name, xCoordinate, yCoordinate)
    }

}


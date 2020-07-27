package com.xyinc.poiservice.service.impl

import com.xyinc.poiservice.model.PointOfInterest
import org.amshove.kluent.shouldBeFalse
import org.amshove.kluent.shouldBeTrue
import org.junit.jupiter.api.Test

/**
 * Unit testing for PoiServiceImpl
 *
 * @author Danilo Guimaraes
 */
class PoiServiceImplTest {

    private val validPoiName : String = "An Awesome Place";

    private val sut: PoiServiceImpl = PoiServiceImpl();


    @Test
    fun `Given a valid PointOfInterest, when validate it, then must return True`() {
        // GIVEN
        val poi = createPointOfInterest(1L, validPoiName, 10, 10);

        // WHEN
        val validateResult = sut.validate(poi);

        // THEN
        validateResult.shouldBeTrue();
    }

    @Test
    fun `Given a PointOfInterest without ID, when validate it, then must return True`() {
        // GIVEN
        val poi = createPointOfInterest(name = validPoiName, xCoordinate = 10, yCoordinate =  10);

        // WHEN
        val validateResult = sut.validate(poi);

        // THEN
        validateResult.shouldBeTrue();
    }

    @Test
    fun `Given a PointOfInterest with XCoordinate equals 0, when validate it, then must return True`() {
        // GIVEN
        val poi = createPointOfInterest(1L, validPoiName, 0, 10);

        // WHEN
        val validateResult = sut.validate(poi);

        // THEN
        validateResult.shouldBeTrue();
    }

    @Test
    fun `Given a PointOfInterest with negative XCoordinate, when validate it, then must return False`() {
        // GIVEN
        val poi = createPointOfInterest(1L, validPoiName, -10, 10);

        // WHEN
        val validateResult = sut.validate(poi);

        // THEN
        validateResult.shouldBeFalse();
    }

    @Test
    fun `Given a PointOfInterest with negative YCoordinate, when validate it, then must return False`() {
        // GIVEN
        val poi = createPointOfInterest(1L, validPoiName, 10, -10);

        // WHEN
        val validateResult = sut.validate(poi);

        // THEN
        validateResult.shouldBeFalse();
    }

    @Test
    fun `Given a PointOfInterest with both X and Y Coordinates as Negative, when validate it, then must return False`() {
        // GIVEN
        val poi = createPointOfInterest(1L, validPoiName, -10, -10);

        // WHEN
        val validateResult = sut.validate(poi);

        // THEN
        validateResult.shouldBeFalse();
    }

    @Test
    fun `Given an unnamed PointOfInterest, when validate it, then must return False`() {
        // GIVEN
        val poi = createPointOfInterest(id = 1L, xCoordinate = 10, yCoordinate = 10);

        // WHEN
        val validateResult = sut.validate(poi);

        // THEN
        validateResult.shouldBeFalse();
    }

    @Test
    fun `Given a PointOfInterest without XCoordinate, when validate it, then must return False`() {
        // GIVEN
        val poi = createPointOfInterest(id = 1L, name = validPoiName, yCoordinate = 10);

        // WHEN
        val validateResult = sut.validate(poi);

        // THEN
        validateResult.shouldBeFalse();
    }

    @Test
    fun `Given a PointOfInterest without YCoordinate, when validate it, then must return False`() {
        // GIVEN
        val poi = createPointOfInterest(id = 1L, name = validPoiName, xCoordinate = 10);

        // WHEN
        val validateResult = sut.validate(poi);

        // THEN
        validateResult.shouldBeFalse();
    }

    private fun createPointOfInterest(
            id: Long? = null,
            name: String = "",
            xCoordinate: Int = -1,
            yCoordinate: Int = -1): PointOfInterest {
        return PointOfInterest(id, name, xCoordinate, yCoordinate)
    }

}


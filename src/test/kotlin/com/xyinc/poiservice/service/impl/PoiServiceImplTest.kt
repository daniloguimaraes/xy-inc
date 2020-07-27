package com.xyinc.poiservice.service.impl

import com.xyinc.poiservice.model.PointOfInterest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertFalse

/**
 * Unit testing for PoiServiceImpl
 *
 * @author Danilo Guimaraes
 */
class PoiServiceImplTest {

    private val validPoiName : String = "An Awesome Place";

    private val sut: PoiServiceImpl = PoiServiceImpl();


    @Test
    fun `Validate A Valid PointOfInterest Must Return True`() {
        val poi = createPointOfInterest(1L, validPoiName, 10, 10);

        assertTrue(sut.validate(poi));
    }

    @Test
    fun `Validate A PointOfInterest Without Id Must Return True`() {
        val poi = createPointOfInterest(name = validPoiName, xCoordinate = 10, yCoordinate =  10);

        assertTrue(sut.validate(poi));
    }

    @Test
    fun `Validate A Zero XCoordinate PointOfInterest Must Return True`() {
        val poi = createPointOfInterest(1L, validPoiName, 0, 10);

        assertTrue(sut.validate(poi));
    }

    @Test
    fun `Validate A Negative XCoordinate PointOfInterest Must Return False`() {
        val poi = createPointOfInterest(1L, validPoiName, -10, 10);

        assertFalse(sut.validate(poi));
    }

    @Test
    fun `Validate A Negative YCoordinate PointOfInterest Must Return False`() {
        val poi = createPointOfInterest(1L, validPoiName, 10, -10);

        assertFalse(sut.validate(poi));
    }

    @Test
    fun `Validate PointOfInterest With Both X and Y Coordinate As Negative Numbers Must Return False`() {
        val poi = createPointOfInterest(1L, validPoiName, -10, -10);

        assertFalse(sut.validate(poi));
    }

    @Test
    fun `Validate An Unnamed PointOfInterest Must Return False`() {
        val poi = createPointOfInterest(id = 1L, xCoordinate = 10, yCoordinate = 10);

        assertFalse(sut.validate(poi));
    }

    @Test
    fun `Validate A PointOfInterest Without XCoordinate Must Return False`() {
        val poi = createPointOfInterest(id = 1L, name = "An Awesome Place", yCoordinate = 10);

        assertFalse(sut.validate(poi));
    }

    private fun createPointOfInterest(
            id: Long? = null,
            name: String = "",
            xCoordinate: Int = -1,
            yCoordinate: Int = -1): PointOfInterest {
        return PointOfInterest(id, name, xCoordinate, yCoordinate)
    }

}


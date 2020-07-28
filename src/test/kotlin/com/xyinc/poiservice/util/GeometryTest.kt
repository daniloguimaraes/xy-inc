package com.xyinc.poiservice.util

import org.amshove.kluent.shouldEqualTo
import org.junit.jupiter.api.Test

/**
 * Unit testing for Geometry
 *
 * @author Danilo Guimaraes
 */
class GeometryTest {

    @Test
    fun `calculate Distance between (-3, -11) and (2,1) must be equals 13`() {
        // GIVEN
        val x1 = -3;
        val y1 = -11;

        val x2 = 2;
        val y2 = 1;

        // WHEN
        val result = Geometry.calculateDistance(x1, y1, x2, y2)

        // THEN
        result shouldEqualTo 13.toDouble()
    }

    @Test
    fun `calculate Distance between (-2, 1) and (6, 7) must be equals 10`() {
        // GIVEN
        val x1 = -2;
        val y1 = 1;

        val x2 = 6;
        val y2 = 7;

        // WHEN
        val result = Geometry.calculateDistance(x1, y1, x2, y2)

        // THEN
        result shouldEqualTo 10.toDouble()
    }

    @Test
    fun `calculate Distance between (0, 0) and (0, 0) must be equals 0`() {
        // GIVEN
        val x1 = 0;
        val y1 = 0;

        val x2 = 0;
        val y2 = 0;

        // WHEN
        val result = Geometry.calculateDistance(x1, y1, x2, y2)

        // THEN
        result shouldEqualTo 0.toDouble()
    }
}
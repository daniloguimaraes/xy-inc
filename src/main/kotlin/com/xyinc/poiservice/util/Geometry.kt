package com.xyinc.poiservice.util

import kotlin.math.hypot

/**
 * Geometry utility class
 *
 * @author Danilo Guimaraes
 */
class Geometry {

    companion object {

        /**
         * Calculates the distance between two points, (x1, y1) and (x2, y2), on the Cartesian plane
         *
         * @return the distance between the two points, as Double
         *
         */
        @JvmStatic
        fun calculateDistance(x1 : Int, y1 : Int, x2 : Int, y2 : Int): Double =
            hypot((x1 - x2).toDouble(), ((y1 - y2).toDouble()))

    }
}
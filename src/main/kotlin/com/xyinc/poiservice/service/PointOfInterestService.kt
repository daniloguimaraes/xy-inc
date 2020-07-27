package com.xyinc.poiservice.service

import com.xyinc.poiservice.model.PointOfInterest

/**
 * Services for Points of Interest.
 *
 * @author Danilo Guimaraes
 */
interface PointOfInterestService {

    /**
     * Validates a {@link PointOfInterest}, which X and Y coordinates must to be a non-negative integer and also
     * must have a name.
     *
     * @param poi the PointOfInterest to be validated.
     * @return `true` case the PointOfInterest is valid, `false` otherwise.
     *
     */
    fun validate(poi : PointOfInterest): Boolean;

}
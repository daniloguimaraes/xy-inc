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
     * @throws InvalidPointOfInterestException case the PointOfInterest is invalid
     *
     */
    fun validate(poi : PointOfInterest);

    /**
     * Returns all point of interests
     *
     * @return all point of interests
     */
    fun findAll(): List<PointOfInterest>;

    /**
     * Adds a new Point of Interest.
     *
     * @param poi the point of interest to be added
     * @return the added point of interest
     */
    fun add(poi: PointOfInterest): PointOfInterest;

}
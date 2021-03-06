package com.xyinc.poiservice.service

import com.xyinc.poiservice.model.PointOfInterest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

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
    fun validate(poi : PointOfInterest)

    /**
     * Returns all point of interests
     *
     * @return all point of interests
     */
    fun findAll(pageable: Pageable): Page<PointOfInterest>

    /**
     * Adds a new Point of Interest.
     *
     * @param poi the point of interest to be added
     * @return the added point of interest
     */
    fun add(poi: PointOfInterest): PointOfInterest

    /**
     * Returns all points of interest nearby x and y coordinates, not farthest than radius.
     *
     * @param xCoordinate the x coordinate
     * @param yCoordinate the y coordinate
     * @param radius the max distance radius allowed to be considered.
     */
    fun findAllNearby(xCoordinate : Int, yCoordinate: Int, radius: Int) : Page<PointOfInterest>

}
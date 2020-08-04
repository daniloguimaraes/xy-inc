package com.xyinc.poiservice.repository

import com.xyinc.poiservice.model.PointOfInterest
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * Spring Data Repository for Point of Interests.
 *
 * @author Danilo Guimaraes
 */
@Repository
interface PointOfInterestRepository : CrudRepository<PointOfInterest, Long> {

    /**
     * Given a point O(x, y) and a radius, find all PoIs in a square.
     *
     * <code>
     * y axis
     *   /\            y'
     *   |             /\
     *   |             |
     *   |  x' <----O(x,y)----> x''
     *   |            |
     *   |           \/
     *   |           y''
     *   |--------------------------> x axis
     * </code>
     *
     */
    @Query("select p from PointOfInterest p where (p.xCoordinate >= :minXCoordinate and p.xCoordinate <= :maxXCoordinate)" +
            " and (p.yCoordinate >= :minYCoordinate and p.yCoordinate <= :maxYCoordinate)")
    fun findAllInSquare(minXCoordinate:Int, maxXCoordinate:Int, minYCoordinate:Int, maxYCoordinate:Int) : List<PointOfInterest>

}
package com.xyinc.poiservice.repository

import com.xyinc.poiservice.model.PointOfInterest
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * Spring Data Repository for Point of Interests.
 *
 * @author Danilo Guimaraes
 */
@Repository
interface PointOfInterestRepository : CrudRepository<PointOfInterest, Long> {

}
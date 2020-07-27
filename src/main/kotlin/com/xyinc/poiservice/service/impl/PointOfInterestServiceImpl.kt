package com.xyinc.poiservice.service.impl

import com.xyinc.poiservice.model.PointOfInterest
import com.xyinc.poiservice.service.PointOfInterestService

class PointOfInterestServiceImpl() : PointOfInterestService {

    override fun validate(poi: PointOfInterest): Boolean {
        return poi.xCoordinate >= 0 && poi.yCoordinate >= 0 && poi.name.isNotEmpty();
    }
}
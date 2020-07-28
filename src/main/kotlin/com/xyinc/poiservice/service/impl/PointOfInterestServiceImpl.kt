package com.xyinc.poiservice.service.impl

import com.xyinc.poiservice.exception.InvalidPointOfInterestException
import com.xyinc.poiservice.model.PointOfInterest
import com.xyinc.poiservice.repository.PointOfInterestRepository
import com.xyinc.poiservice.service.PointOfInterestService
import com.xyinc.poiservice.util.Geometry
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PointOfInterestServiceImpl(@Autowired val pointOfInterestRepository: PointOfInterestRepository) : PointOfInterestService {

    override fun validate(poi: PointOfInterest) {
        if (poi.xCoordinate < 0) {
            throw InvalidPointOfInterestException("X Coordinate must be greater or equals than 0");
        }

        if (poi.yCoordinate < 0) {
            throw InvalidPointOfInterestException("Y Coordinate must be greater or equals than 0");
        }

        if (poi.name.isEmpty()) {
            throw InvalidPointOfInterestException("Name should not be null or empty");
        }
    }

    override fun findAll(): List<PointOfInterest> {
        return pointOfInterestRepository.findAll().toList();
    }

    override fun add(poi: PointOfInterest): PointOfInterest {
        validate(poi);

        return pointOfInterestRepository.save(poi);
    }

    override fun findAllNearby(xCoordinate: Int, yCoordinate: Int, radius: Int): List<PointOfInterest> {
        return findAll().filter {
            Geometry.calculateDistance(it.xCoordinate, it.yCoordinate, xCoordinate, yCoordinate) <= radius
        }
    }

}
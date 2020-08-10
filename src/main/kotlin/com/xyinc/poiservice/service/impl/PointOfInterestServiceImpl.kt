package com.xyinc.poiservice.service.impl

import com.xyinc.poiservice.exception.InvalidPointOfInterestException
import com.xyinc.poiservice.model.PointOfInterest
import com.xyinc.poiservice.repository.PointOfInterestRepository
import com.xyinc.poiservice.service.PointOfInterestService
import com.xyinc.poiservice.util.Geometry
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PointOfInterestServiceImpl(@Autowired val pointOfInterestRepository: PointOfInterestRepository) : PointOfInterestService {

    override fun validate(poi: PointOfInterest) {
        if (poi.xCoordinate < 0) {
            throw InvalidPointOfInterestException("X Coordinate must be greater or equals than 0")
        }

        if (poi.yCoordinate < 0) {
            throw InvalidPointOfInterestException("Y Coordinate must be greater or equals than 0")
        }

        if (poi.name.isEmpty()) {
            throw InvalidPointOfInterestException("Name should not be null or empty")
        }
    }

    override fun findAll(pageable: Pageable): Page<PointOfInterest> {
        return pointOfInterestRepository.findAll(pageable)
    }

    override fun add(poi: PointOfInterest): PointOfInterest {
        validate(poi)

        return pointOfInterestRepository.save(poi)
    }

    override fun findAllNearby(xCoordinate: Int, yCoordinate: Int, radius: Int): Page<PointOfInterest> {
        val minXCoordinate = xCoordinate - radius
        val maxXCoordinate = xCoordinate + radius
        val minYCoordinate = yCoordinate - radius
        val maxYCoordinate = yCoordinate + radius

        val allPointOfInterestInSquare = pointOfInterestRepository.findAllInSquare(
                minXCoordinate,
                maxXCoordinate,
                minYCoordinate,
                maxYCoordinate)

        return PageImpl<PointOfInterest>(allPointOfInterestInSquare.filter {
            Geometry.calculateDistance(it.xCoordinate, it.yCoordinate, xCoordinate, yCoordinate) <= radius
        })
    }

}
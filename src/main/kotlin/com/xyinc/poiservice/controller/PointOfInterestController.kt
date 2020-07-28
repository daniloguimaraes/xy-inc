package com.xyinc.poiservice.controller

import com.xyinc.poiservice.model.PointOfInterest
import com.xyinc.poiservice.service.PointOfInterestService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Point of Interest REST Controller
 *
 * @author Danilo Guimaraes
 */
@RestController
@RequestMapping("/api/poi")
internal class PointOfInterestController(val pointOfInterestService: PointOfInterestService) {

    @GetMapping
    fun findAll(): ResponseEntity<List<PointOfInterest>> {
        return ResponseEntity.ok(pointOfInterestService.findAll());
    }

    @PostMapping
    fun add(@RequestBody poi: PointOfInterest): PointOfInterest {
        return pointOfInterestService.add(poi);
    }

    @GetMapping(path= ["/nearby/{x}/{y}/{radius}"])
    fun findAllNearby(@PathVariable(required = true) x: Int,
                    @PathVariable(required = true) y: Int,
                    @PathVariable radius: Int = 10): ResponseEntity<List<PointOfInterest>> {
        return ResponseEntity.ok(pointOfInterestService.findAllNearby(x, y, radius));
    }

}
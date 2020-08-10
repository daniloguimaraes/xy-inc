package com.xyinc.poiservice.controller

import com.xyinc.poiservice.model.PointOfInterest
import com.xyinc.poiservice.service.PointOfInterestService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.*
import java.lang.StringBuilder

/**
 * Point of Interest REST Controller
 *
 * @author Danilo Guimaraes
 */
@Api("Point of Interest endpoints")
@RestController
@RequestMapping("/api/poi")
internal class PointOfInterestController(val pointOfInterestService: PointOfInterestService) {

    @ApiOperation(value = "Returns all Points of Interest")
    @GetMapping
    fun findAll(): ResponseEntity<List<PointOfInterest>> {
        return ok(pointOfInterestService.findAll());
    }

    @ApiOperation(value = "Creates a new Point of Interest")
    @PostMapping
    fun add(@RequestBody poi: PointOfInterest): PointOfInterest {
        return pointOfInterestService.add(poi);
    }

    @ApiOperation("Returns all nearby Points of Interest")
    @GetMapping(path= ["/nearby/{x}/{y}/{radius}"])
    fun findAllNearby(@PathVariable(required = true) @ApiParam("X Coordinate") x: Int,
                      @PathVariable(required = true) @ApiParam("Y Coordinate") y: Int,
                      @PathVariable @ApiParam(value = "Radius", defaultValue = "10") radius: Int = 10): ResponseEntity<List<PointOfInterest>> {
        return ok(pointOfInterestService.findAllNearby(x, y, radius));
    }

}
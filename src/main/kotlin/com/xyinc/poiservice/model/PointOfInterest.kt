package com.xyinc.poiservice.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * Data class to represent a POI (Point of Interest).
 *
 * @author Danilo Guimaraes
 */
@Entity
data class PointOfInterest(
        @Id @GeneratedValue
        var id: Long? = null,

        @Column(nullable = false)
        var name: String,

        @Column(nullable = false)
        var xCoordinate: Int,

        @Column(nullable = false)
        var yCoordinate: Int) {

}
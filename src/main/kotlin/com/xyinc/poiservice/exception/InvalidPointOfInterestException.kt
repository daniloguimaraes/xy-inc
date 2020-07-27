package com.xyinc.poiservice.exception

/**
 * Exception to represent an invalid point of interest.
 *
 * @author Danilo Guimaraes
 */
class InvalidPointOfInterestException (override val message: String) : RuntimeException(message)
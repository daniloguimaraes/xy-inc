package com.xyinc.poiservice.config

import com.xyinc.poiservice.exception.InvalidPointOfInterestException
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Controller advice for exception handling.
 *
 * @author Danilo Guimaraes
 */
@Configuration
class ControllerAdvice

    @ExceptionHandler(value = [InvalidPointOfInterestException::class])
    @ResponseBody
    fun onInvalidPointOfInterestException(exception: InvalidPointOfInterestException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
    }
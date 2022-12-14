package com.fdymendo.javeriana.citas.utils

import com.fdymendo.javeriana.citas.model.ResponseDefault
import com.fdymendo.javeriana.citas.model.ResponseError
import com.fdymendo.javeriana.infracciones.handlers.ApplicationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class GenericMethods {
    companion object {
        fun responseOk(): ResponseEntity<String> {
            return ResponseEntity.ok().body(HttpStatus.OK.reasonPhrase)
        }

        fun responseOk(responseDefault: ResponseDefault): ResponseEntity<ResponseDefault> {
            return ResponseEntity.ok(responseDefault)
        }

        fun genericResponse(
            applicationException: ApplicationException
        ): ResponseEntity<ResponseError> {
            return ResponseEntity.status(applicationException.getHttpStatus())
                .body(
                    ResponseError(
                        applicationException.message ?: HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase
                    )
                )
        }

        fun responseBadRequest(): ResponseEntity<ResponseDefault> {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
        }

        fun responseNotFound() = ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseDefault(null, null))

        fun responseError500(message: String): ResponseEntity<ResponseError> {
            return ResponseEntity.internalServerError().body(ResponseError(message))
        }

    }
}
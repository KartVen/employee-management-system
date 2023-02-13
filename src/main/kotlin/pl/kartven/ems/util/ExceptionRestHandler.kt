package pl.kartven.ems.util

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import pl.kartven.ems.exception.BadRequestException
import pl.kartven.ems.exception.NotFoundException
import java.util.*

@RestControllerAdvice
class ExceptionRestHandler {
    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun notFoundException(ex: NotFoundException, request: WebRequest): ResponseEntity<ExceptionRestResponse> {
        val message = ExceptionRestResponse(
            Date(),
            HttpStatus.NOT_FOUND.value(),
            ex.message ?: HttpStatus.NOT_FOUND.reasonPhrase,
            request.getDescription(false)
        )
        return ResponseEntity<ExceptionRestResponse>(message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun internalServerErrorException(ex: Exception, request: WebRequest): ResponseEntity<ExceptionRestResponse> {
        val message = ExceptionRestResponse(
            Date(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            ex.message ?: HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase,
            request.getDescription(false)
        )
        return ResponseEntity<ExceptionRestResponse>(message, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(BadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun badRequestException(ex: BadRequestException, request: WebRequest): ResponseEntity<ExceptionRestResponse> {
        val message = ExceptionRestResponse(
            Date(),
            HttpStatus.BAD_REQUEST.value(),
            ex.message ?: HttpStatus.BAD_REQUEST.reasonPhrase,
            request.getDescription(false)
        )
        return ResponseEntity<ExceptionRestResponse>(message, HttpStatus.BAD_REQUEST)
    }
}
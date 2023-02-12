package pl.kartven.ems.util

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import pl.kartven.ems.exception.BadRequestException
import pl.kartven.ems.exception.NotFoundException
import java.util.*

@ControllerAdvice
class ExceptionRestHandler {
    @ExceptionHandler(NotFoundException::class)
    fun notFoundException(ex: NotFoundException, request: WebRequest): ResponseEntity<ExceptionRestMessage> {
        val message = ExceptionRestMessage(
            Date(),
            HttpStatus.NOT_FOUND.value(),
            ex.message ?: HttpStatus.NOT_FOUND.reasonPhrase,
            request.getDescription(false)
        )
        return ResponseEntity<ExceptionRestMessage>(message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(Exception::class)
    fun internalServerErrorException(ex: Exception, request: WebRequest): ResponseEntity<ExceptionRestMessage> {
        val message = ExceptionRestMessage(
            Date(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            ex.message ?: HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase,
            request.getDescription(false)
        )
        return ResponseEntity<ExceptionRestMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(BadRequestException::class)
    fun badRequestException(ex: BadRequestException, request: WebRequest): ResponseEntity<ExceptionRestMessage> {
        val message = ExceptionRestMessage(
            Date(),
            HttpStatus.BAD_REQUEST.value(),
            ex.message ?: HttpStatus.BAD_REQUEST.reasonPhrase,
            request.getDescription(false)
        )
        return ResponseEntity<ExceptionRestMessage>(message, HttpStatus.BAD_REQUEST)
    }
}
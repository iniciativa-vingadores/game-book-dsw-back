package com.initvingadores.gamebook.system.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.RuntimeException
import java.util.*
import javax.validation.ConstraintViolationException

class DataException(override val message: String?) : RuntimeException(message)

class NotFoundException(override val message: String?): RuntimeException(message)

class AuthenticationException(override val message: String?): RuntimeException(message)

class AuthorizationException(override val message: String?): RuntimeException(message)

@ControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler
    fun handlerException(exception: DataException) : ResponseEntity<CustomError> {
        val error = CustomError(
                HttpStatus.PRECONDITION_FAILED.value(),
                Date(System.currentTimeMillis()),
                exception.message)

        return ResponseEntity(error, HttpStatus.PRECONDITION_FAILED)
    }

    @ExceptionHandler
    fun handlerException(exception: NotFoundException) : ResponseEntity<CustomError> {
        val error = CustomError(
                HttpStatus.BAD_REQUEST.value(),
                Date(System.currentTimeMillis()),
                exception.message)

        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handlerException(exception: AuthenticationException) : ResponseEntity<CustomError> {
        val error = CustomError(
                HttpStatus.FORBIDDEN.value(),
                Date(System.currentTimeMillis()),
                exception.message)

        return ResponseEntity(error, HttpStatus.FORBIDDEN)
    }

    @ExceptionHandler
    fun handlerException(exception: AuthorizationException) : ResponseEntity<CustomError> {
        val error = CustomError(
                HttpStatus.UNAUTHORIZED.value(),
                Date(System.currentTimeMillis()),
                exception.message)

        return ResponseEntity(error, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler
    fun handlerException(exception: ConstraintViolationException)
            : ResponseEntity<CustomError> {
        val error = CustomError(
                HttpStatus.BAD_REQUEST.value(),
                Date(System.currentTimeMillis()),
                exception.message)

        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }
}
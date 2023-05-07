package com.example.notice.global.exception

import com.example.notice.global.model.ApiResponse
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun internalServerError(e: Exception): ResponseEntity<ApiResponse<Unit>> {

        return ResponseEntity.internalServerError()
            .body(
                ApiResponse.error(
                    INTERNAL_SERVER_ERROR.value(),
                    "서버 에러가 발생했습니다. 관리자에게 문의해주세요"
                )
            )
    }
}
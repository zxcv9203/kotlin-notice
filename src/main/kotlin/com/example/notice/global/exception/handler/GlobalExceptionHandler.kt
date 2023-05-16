package com.example.notice.global.exception.handler

import com.example.notice.global.exception.NotExistPostException
import com.example.notice.global.model.ApiResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    val log: Logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(NotExistPostException::class)
    fun notFoundPost(e: NotExistPostException): ResponseEntity<ApiResponse<Unit>> {
        log.warn("게시글 ID : " + e.id + "\n" + e.stackTraceToString())

        return ResponseEntity(
            ApiResponse.error(
                NOT_FOUND.value(),
                "요청한 게시글을 찾을 수 없습니다."
            ),
            NOT_FOUND
        )
    }

    @ExceptionHandler(Exception::class)
    fun internalServerError(e: Exception): ResponseEntity<ApiResponse<Unit>> {
        log.error(e.message + "\n" + e.stackTraceToString())

        return ResponseEntity.internalServerError()
            .body(
                ApiResponse.error(
                    INTERNAL_SERVER_ERROR.value(),
                    "서버 에러가 발생했습니다. 관리자에게 문의해주세요"
                )
            )
    }
}
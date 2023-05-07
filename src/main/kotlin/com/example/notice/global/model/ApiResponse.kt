package com.example.notice.global.model

import com.fasterxml.jackson.annotation.JsonInclude

data class ApiResponse<T>(
    val status: Int,
    val message: String? = "",
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val body: T? = null
) {
    companion object {
        fun error(status: Int, message: String?): ApiResponse<Unit> = ApiResponse(
            status = status,
            message = message
        )

        fun <T> success(status: Int, message: String?, body: T?): ApiResponse<T> =
            ApiResponse(
                status = status,
                message = message,
                body = body
            )

        fun <T> success(status: Int, message: String?): ApiResponse<T> =
            ApiResponse(
                status = status,
                message = message,
            )
    }
}
package com.example.notice.global.model

data class ApiResponse<T>(
    val status: Int,
    val message: String? = "",
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
    }
}
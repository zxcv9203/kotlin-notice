package com.example.notice.global.exception

class InvalidPasswordException(
    private val requestPassword: String,
    private val savePassword: String
) : InvalidValueException() {
    val request get() = requestPassword
    val save get() = savePassword
}
package com.example.notice.global.exception

class NotExistPostException(private val postId: Long) : NotFoundException() {

    val id get() = postId
}
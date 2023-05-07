package com.example.notice.domain.post.controller.dto.request

data class PostSaveRequest(
    val memberName: String,
    val password: String,
    val title: String,
    val content: String
)

package com.example.notice.domain.post.controller.dto.request

data class PostUpdateRequest(
    val password: String,
    val title: String,
    val content: String
)
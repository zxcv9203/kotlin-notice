package com.example.notice.domain.post.converter

import com.example.notice.domain.post.controller.dto.request.PostSaveRequest
import com.example.notice.domain.post.model.Post

fun PostSaveRequest.toEntity(memberId: Long) = Post(
    title = this.title,
    content = this.content,
    memberId = memberId
)
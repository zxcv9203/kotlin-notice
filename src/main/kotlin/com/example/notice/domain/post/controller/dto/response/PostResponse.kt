package com.example.notice.domain.post.controller.dto.response

import com.example.notice.domain.member.api.dto.response.MemberResponse
import java.time.LocalDateTime

data class PostResponse(
    val id: Long,
    val member: MemberResponse,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
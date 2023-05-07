package com.example.notice.domain.post.controller.dto.response

import com.example.notice.domain.member.api.dto.response.MemberResponse
import java.time.LocalDateTime

data class PostSimpleResponse(
    val id: Long,
    val title: String,
    val member: MemberResponse,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
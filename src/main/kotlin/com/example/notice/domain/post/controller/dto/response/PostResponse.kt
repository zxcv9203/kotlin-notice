package com.example.notice.domain.post.controller.dto.response

import com.example.notice.domain.member.api.dto.response.MemberResponse
import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDateTime

data class PostResponse @QueryProjection constructor(
    val id: Long,
    val member: MemberResponse,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
package com.example.notice.domain.post.controller.dto.response

import com.example.notice.domain.member.api.dto.response.MemberResponse
import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDateTime

data class PostSimpleResponse @QueryProjection constructor(
    val id: Long,
    val title: String,
    val member: MemberResponse,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
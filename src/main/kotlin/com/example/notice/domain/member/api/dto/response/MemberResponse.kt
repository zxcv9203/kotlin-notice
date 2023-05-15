package com.example.notice.domain.member.api.dto.response

import com.querydsl.core.annotations.QueryProjection

data class MemberResponse @QueryProjection constructor(
    val id: Long,
    val nickName: String
)
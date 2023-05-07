package com.example.notice.stub

import com.example.notice.domain.member.api.dto.response.MemberResponse

fun getMemberResponse() = MemberResponse(
    id = 0,
    nickName = "testUser"
)
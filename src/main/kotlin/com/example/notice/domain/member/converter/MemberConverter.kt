package com.example.notice.domain.member.converter

import com.example.notice.domain.member.api.dto.request.MemberSaveRequest
import com.example.notice.domain.member.model.Member
import com.example.notice.domain.post.controller.dto.request.PostSaveRequest

fun PostSaveRequest.toMemberSaveRequest() = MemberSaveRequest(
    name = this.memberName,
    password = this.password,
)

fun MemberSaveRequest.toEntity() = Member(
    name = this.name,
    password = this.password,
)
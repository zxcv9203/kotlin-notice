package com.example.notice.stub

import com.example.notice.domain.post.controller.dto.request.PostDeleteRequest
import com.example.notice.domain.post.controller.dto.request.PostSaveRequest
import com.example.notice.domain.post.controller.dto.request.PostUpdateRequest
import com.example.notice.domain.post.controller.dto.response.PostResponse
import com.example.notice.domain.post.controller.dto.response.PostSimpleResponse
import java.time.LocalDateTime

fun getPostSimpleResponse() = PostSimpleResponse(
    id = 0, title = "테스트", getMemberResponse(), LocalDateTime.now(), LocalDateTime.now()
)

fun getPostResponse() = PostResponse(
    id = 0,
    getMemberResponse(),
    title = "testTitle",
    content = "testContent",
    LocalDateTime.now(),
    LocalDateTime.now()
)

fun getPostSaveRequest() = PostSaveRequest(
    memberName = "testName", password = "testPassword", title = "testTitle", content = "testContent"
)

fun getPostUpdateRequest() = PostUpdateRequest(
    password = "testPassword", title = "testTitle", content = "testContent"
)

fun getPostDeleteRequest() = PostDeleteRequest(
    password = "testPassword"
)
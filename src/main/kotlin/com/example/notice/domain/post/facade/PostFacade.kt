package com.example.notice.domain.post.facade

import com.example.notice.domain.member.api.dto.response.MemberResponse
import com.example.notice.domain.post.controller.dto.request.PostDeleteRequest
import com.example.notice.domain.post.controller.dto.request.PostSaveRequest
import com.example.notice.domain.post.controller.dto.request.PostUpdateRequest
import com.example.notice.domain.post.controller.dto.response.PostResponse
import com.example.notice.domain.post.controller.dto.response.PostResponses
import com.example.notice.domain.post.service.PostService
import com.example.notice.global.model.IdResponse
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class PostFacade(
    private val postService: PostService
) {

    fun findAll() : PostResponses{
        // 차후 구현
        return PostResponses(listOf())
    }

    fun findById(postId: Long) : PostResponse{
        return PostResponse(0, MemberResponse(0, ""), "", "", LocalDateTime.now(), LocalDateTime.now())
    }

    fun save(request: PostSaveRequest) : IdResponse{
        return IdResponse(0)
    }

    fun update(request: PostUpdateRequest, postId: Long): PostResponse {
        return PostResponse(0, MemberResponse(0, ""), "", "", LocalDateTime.now(), LocalDateTime.now())
    }

    fun deleteById(request: PostDeleteRequest, postId: Long) {
        println("aaa")
    }
}
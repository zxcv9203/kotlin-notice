package com.example.notice.domain.post.facade

import com.example.notice.domain.member.api.dto.response.MemberResponse
import com.example.notice.domain.member.converter.toMemberSaveRequest
import com.example.notice.domain.member.service.MemberService
import com.example.notice.domain.post.controller.dto.request.PostDeleteRequest
import com.example.notice.domain.post.controller.dto.request.PostSaveRequest
import com.example.notice.domain.post.controller.dto.request.PostUpdateRequest
import com.example.notice.domain.post.controller.dto.response.PostResponse
import com.example.notice.domain.post.controller.dto.response.PostResponses
import com.example.notice.domain.post.service.PostService
import com.example.notice.global.model.IdResponse
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Component
@Transactional
class PostFacade(
    private val postService: PostService,
    private val memberService: MemberService
) {

    fun findAll() : PostResponses{
        val posts = postService.findAll()

        return PostResponses(posts)
    }

    fun findById(postId: Long) : PostResponse{
        // 게시글 단일 정보 반환
        return PostResponse(0, MemberResponse(0, ""), "", "", LocalDateTime.now(), LocalDateTime.now())
    }

    fun save(request: PostSaveRequest) : IdResponse{
        val memberId = memberService.save(request.toMemberSaveRequest())

        val postId = postService.save(request, memberId)

        return IdResponse(postId)
    }

    fun update(request: PostUpdateRequest, postId: Long): PostResponse {
        // 게시글 및 패스워드 확인

        // 업데이트 처리

        // 게시글 정보 반환
        return PostResponse(0, MemberResponse(0, ""), "", "", LocalDateTime.now(), LocalDateTime.now())
    }

    fun deleteById(request: PostDeleteRequest, postId: Long) {
        // 게시글 및 패스워드 확인

        // 삭제 처리
        println("aaa")
    }
}